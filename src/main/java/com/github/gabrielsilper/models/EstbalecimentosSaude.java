package com.github.gabrielsilper.models;

import java.math.BigDecimal;

public class EstbalecimentosSaude {
    private String municipio;
    private String nome;
    private String logradouro;
    private String bairro;
    private String latitude;
    private String longitude;
    private String cep;

    public EstbalecimentosSaude() {
    }

    public EstbalecimentosSaude(String municipio, String nome, String logradouro, String bairro, String latitude, String longitude, String cep) {
        this.municipio = municipio;
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cep = cep;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "EstbalecimentosSaude{" +
                "municipio='" + municipio + '\'' +
                ", nome='" + nome + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
