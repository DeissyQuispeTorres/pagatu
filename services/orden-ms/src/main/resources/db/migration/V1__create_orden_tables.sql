CREATE TABLE ordenes (
    id              BIGSERIAL PRIMARY KEY,
    cliente_nombre  VARCHAR(150) NOT NULL,
    fecha           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado          VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    total           NUMERIC(10,2) NOT NULL DEFAULT 0
);

CREATE TABLE detalle_ordenes (
    id              BIGSERIAL PRIMARY KEY,
    orden_id        BIGINT NOT NULL,
    producto_id     BIGINT NOT NULL,
    cantidad        INT NOT NULL CHECK (cantidad > 0),
    precio_unitario NUMERIC(10,2) NOT NULL CHECK (precio_unitario >= 0),
    CONSTRAINT fk_detalle_orden
        FOREIGN KEY (orden_id) REFERENCES ordenes(id) ON DELETE CASCADE
);

CREATE INDEX idx_detalle_orden_id ON detalle_ordenes(orden_id);