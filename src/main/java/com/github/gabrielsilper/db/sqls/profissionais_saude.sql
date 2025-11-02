CREATE TABLE profissionais_saude (
    municipio VARCHAR(8) NOT NULL,
    quantidade_medicos BIGINT NULL,
    quantidade_enfermeiros BIGINT NULL,
    CONSTRAINT fk_profissionais_saude_municipio
        FOREIGN KEY (municipio) REFERENCES municipios(codigo_ibge)
);