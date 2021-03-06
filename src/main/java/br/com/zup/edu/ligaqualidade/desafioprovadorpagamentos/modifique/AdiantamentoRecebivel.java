package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;

public class AdiantamentoRecebivel {

    private Integer idTransacao;
    private Double taxa;

    public AdiantamentoRecebivel(Integer idTransacao, Double taxa) {
        this.idTransacao = idTransacao;
        this.taxa = taxa;
    }

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }
}
