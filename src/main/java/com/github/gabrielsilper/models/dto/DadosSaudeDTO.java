package com.github.gabrielsilper.models.dto;

import com.github.gabrielsilper.models.EstbalecimentosSaude;
import java.util.List;

public class DadosSaudeDTO {

    private int quantidadeMedicos;
    private int quantidadeEnfermeiros;
    private int quantidadeUBS;
    private List<EstbalecimentosSaude> listaUBS;
    private String mensagemErro;

    public DadosSaudeDTO() {
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

    public int getQuantidadeUBS() {
        return quantidadeUBS;
    }

    public void setQuantidadeUBS(int quantidadeUBS) {
        this.quantidadeUBS = quantidadeUBS;
    }

    public List<EstbalecimentosSaude> getListaUBS() {
        return listaUBS;
    }

    public void setListaUBS(List<EstbalecimentosSaude> listaUBS) {
        this.listaUBS = listaUBS;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}