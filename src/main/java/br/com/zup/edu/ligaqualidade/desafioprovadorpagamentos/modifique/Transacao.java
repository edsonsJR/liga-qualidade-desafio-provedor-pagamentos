package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private Double valor;
    private String metodoPagamento;
    private String numeroCartao;
    private String nomeCartao;
    private LocalDate validade;
    private Integer cvv;
    private Integer idTransacao;

    public Transacao(Double valor, String metodoPagamento, String numeroCartao, String nomeCartao, LocalDate validade, Integer cvv, Integer idTransacao) {
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.numeroCartao = numeroCartao;
        this.nomeCartao = nomeCartao;
        this.validade = validade;
        this.cvv = cvv;
        this.idTransacao = idTransacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }
}
