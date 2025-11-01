CREATE TABLE populacao_total (
    municipio VARCHAR(8) PRIMARY KEY,
    populacao_masc BIGINT NOT NULL,
    populacao_fem BIGINT NOT NULL,
    CONSTRAINT fk_populacao_municipio
        FOREIGN KEY (municipio) REFERENCES municipios(codigo_ibge)
);