CREATE TABLE municipios (
    codigo_ibge VARCHAR(8) PRIMARY KEY,
    uf VARCHAR(5) NOT NULL,
    municipio VARCHAR(100) NOT NULL,
    CONSTRAINT fk_municipio_estado
        FOREIGN KEY (uf) REFERENCES estados(codigo_ibge)
);