CREATE TABLE productos (
    id              BIGSERIAL PRIMARY KEY,
    nombre          VARCHAR(150) NOT NULL,
    precio          NUMERIC(10,2) NOT NULL,
    activo          BOOLEAN NOT NULL DEFAULT true
);

INSERT INTO productos (nombre, precio, activo) VALUES ('Comida para Perro 5kg', 50.00, true);