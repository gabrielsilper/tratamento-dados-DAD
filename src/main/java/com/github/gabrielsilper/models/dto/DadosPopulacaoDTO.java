package com.github.gabrielsilper.models.dto;

public class DadosPopulacaoDTO {

    private long populacaoGeral;
    private long populacaoMasculina;
    private long populacaoFeminina;
    private long faixa0a10;
    private long faixa11a20;

    public DadosPopulacaoDTO() {
    }

    public long getFaixa21a30() {
        return faixa21a30;
    }

    public void setFaixa21a30(long faixa21a30) {
        this.faixa21a30 = faixa21a30;
    }

    public long getPopulacaoGeral() {
        return populacaoGeral;
    }

    public void setPopulacaoGeral(long populacaoGeral) {
        this.populacaoGeral = populacaoGeral;
    }

    public long getPopulacaoMasculina() {
        return populacaoMasculina;
    }

    public void setPopulacaoMasculina(long populacaoMasculina) {
        this.populacaoMasculina = populacaoMasculina;
    }

    public long getPopulacaoFeminina() {
        return populacaoFeminina;
    }

    public void setPopulacaoFeminina(long populacaoFeminina) {
        this.populacaoFeminina = populacaoFeminina;
    }

    public long getFaixa0a10() {
        return faixa0a10;
    }

    public void setFaixa0a10(long faixa0a10) {
        this.faixa0a10 = faixa0a10;
    }

    public long getFaixa11a20() {
        return faixa11a20;
    }

    public void setFaixa11a20(long faixa11a20) {
        this.faixa11a20 = faixa11a20;
    }

    public long getFaixa31a40() {
        return faixa31a40;
    }

    public void setFaixa31a40(long faixa31a40) {
        this.faixa31a40 = faixa31a40;
    }

    public long getFaixa41Mais() {
        return faixa41Mais;
    }

    public void setFaixa41Mais(long faixa41Mais) {
        this.faixa41Mais = faixa41Mais;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    private long faixa21a30;
    private long faixa31a40;
    private long faixa41Mais;
    private String mensagemErro;
}