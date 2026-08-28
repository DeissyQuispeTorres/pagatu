package pe.edu.upeu.orden.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrdenRequest {

    @NotBlank(message = "El cliente es obligatorio")
    private String clienteNombre;

    @NotNull(message = "El total es obligatorio")
    private BigDecimal total;

    @NotNull(message = "Los detalles son obligatorios")
    @Valid
    private List<DetalleRequest> detalles;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DetalleRequest {
        @NotNull(message = "productoId obligatorio")
        private Long productoId;

        @NotNull(message = "cantidad obligatoria")
        private Integer cantidad;

        @NotNull(message = "precioUnitario obligatorio")
        private BigDecimal precioUnitario;
    }
}