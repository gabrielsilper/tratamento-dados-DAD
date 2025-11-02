package com.github.gabrielsilper.models;

public class ProfissionaisSaude {
    private String municipio;
    private Integer quantidadeMedicos;
    private Integer quantidadeEnfermeiros;

    public ProfissionaisSaude() {
    }

    public ProfissionaisSaude(String municipio, Integer quantidadeMedicos, Integer quantidadeEnfermeiros) {
        this.municipio = municipio;
        this.quantidadeMedicos = quantidadeMedicos;
        this.quantidadeEnfermeiros = quantidadeEnfermeiros;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getQuantidadeMedicos() {
        return quantidadeMedicos;
    }

    public void setQuantidadeMedicos(int quantidadeMedicos) {
        this.quantidadeMedicos = quantidadeMedicos;
    }

    public int getQuantidadeEnfermeiros() {
        return quantidadeEnfermeiros;
    }

    public void setQuantidadeEnfermeiros(int quantidadeEnfermeiros) {
        this.quantidadeEnfermeiros = quantidadeEnfermeiros;
    }

    @Override
    public String toString() {
        return "ProfissionaisSaudeMunicipio{" +
                "municipio='" + municipio + '\'' +
                ", quantidadeMedicos=" + quantidadeMedicos +
                ", quantidadeEnfermeiros=" + quantidadeEnfermeiros +
                '}';
    }
}
