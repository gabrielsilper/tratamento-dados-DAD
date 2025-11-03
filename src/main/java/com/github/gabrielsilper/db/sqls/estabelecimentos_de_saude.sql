CREATE TABLE estabelecimentos_de_saude (
    municipio VARCHAR(8) NOT NULL,
    nome VARCHAR(120) NOT NULL,
    logradouro VARCHAR(120) NOT NULL,
    bairro VARCHAR(60) NOT NULL,
    latitude VARCHAR(50),
    longitude VARCHAR(50),
    cep VARCHAR(9) NOT NULL,
    CONSTRAINT fk_estabelecimentos_municipio
                FOREIGN KEY (municipio) REFERENCES municipios(codigo_ibge)
)