package com.github.gabrielsilper.models;

public class PopulacaoTotal {
    private String municipio;
    private String populacaoMasc;
    private String populacaoFem;

    public PopulacaoTotal(){

    }

    public PopulacaoTotal(String municipio, String populacaoMasc, String populacaoFem) {
        this.municipio = municipio;
        this.populacaoMasc = populacaoMasc;
        this.populacaoFem = populacaoFem;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPopulacaoMasc() {
        return populacaoMasc;
    }

    public void setPopulacaoMasc(String populacaoMasc) {
        this.populacaoMasc = populacaoMasc;
    }

    public String getPopulacaoFem() {
        return populacaoFem;
    }

    public void setPopulacaoFem(String populacaoFem) {
        this.populacaoFem = populacaoFem;
    }

    @Override
    public String toString() {
        return "Populacao{" +
                "municipio='" + municipio + '\'' +
                ", populacaoMasc='" + populacaoMasc + '\'' +
                ", populacaoFem='" + populacaoFem + '\'' +
                '}';
    }
}

