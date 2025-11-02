CREATE TABLE populacao_total (
    municipio VARCHAR(8) NOT NULL,
    populacao_masc BIGINT NOT NULL,
    populacao_fem BIGINT NOT NULL,
    CONSTRAINT fk_populacao_municipio
        FOREIGN KEY (municipio) REFERENCES municipios(codigo_ibge)
);


CREATE TABLE populacao_faixa_etaria (
    municipio VARCHAR(8) NOT NULL,
    populacao_0_10 BIGINT NOT NULL,
    populacao_11_20 BIGINT NOT NULL,
    populacao_21_30 BIGINT NOT NULL,
    populacao_31_40 BIGINT NOT NULL,
    populacao_41_mais BIGINT NOT NULL,
    CONSTRAINT fk_populacao_faixa_municipio
            FOREIGN KEY (municipio) REFERENCES municipios(codigo_ibge)
);