CREATE TABLE unidades_basica_saude (
    municipio VARCHAR(8) NOT NULL,
    nome VARCHAR(120) NOT NULL,
    logradouro VARCHAR(120) NOT NULL,
    bairro VARCHAR(60) NOT NULL,
    latitude DECIMAL(10, 7) NOT NULL,
    longitude DECIMAL(10, 7) NOT NULL,
    CONSTRAINT fk_ubs_municipio
                FOREIGN KEY (municipio) REFERENCES municipios(codigo_ibge)
)