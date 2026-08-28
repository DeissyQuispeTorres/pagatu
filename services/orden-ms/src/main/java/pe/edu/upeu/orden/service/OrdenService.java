package pe.edu.upeu.orden.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.orden.dto.OrdenRequest;
import pe.edu.upeu.orden.entity.DetalleOrden;
import pe.edu.upeu.orden.entity.Orden;
import pe.edu.upeu.orden.exception.ResourceNotFoundException;
import pe.edu.upeu.orden.repository.OrdenRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository repo;

    @Transactional
    public Orden crear(OrdenRequest req) {
        Orden o = Orden.builder()
                .clienteNombre(req.getClienteNombre())
                .fecha(LocalDateTime.now())
                .estado("PENDIENTE")
                .total(req.getTotal())
                .build();

        List<DetalleOrden> detalles = req.getDetalles().stream()
                .map(d -> DetalleOrden.builder()
                        .orden(o)
                        .productoId(d.getProductoId())
                        .cantidad(d.getCantidad())
                        .precioUnitario(d.getPrecioUnitario())
                        .build())
                .toList();

        o.setDetalles(detalles);
        return repo.save(o);
    }

    @Transactional(readOnly = true)
    public List<Orden> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Orden obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden " + id + " no encontrada"));
    }

    @Transactional
    public Orden actualizarEstado(Long id, String estado) {
        Orden o = obtener(id);
        o.setEstado(estado);
        return repo.save(o);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Orden " + id + " no encontrada");
        }
        repo.deleteById(id);
    }
}