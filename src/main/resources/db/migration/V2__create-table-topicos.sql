CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(1000) NOT NULL,
    fecha_de_creacion DATETIME NOT NULL,
    curso VARCHAR(100) NOT NULL,
    autor_id BIGINT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);
