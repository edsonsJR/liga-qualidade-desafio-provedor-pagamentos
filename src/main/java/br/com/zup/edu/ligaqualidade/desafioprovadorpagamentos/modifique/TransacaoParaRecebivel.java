package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransacaoParaRecebivel {
    private BigDecimal valor;
    private String metodoPagamento;
    private LocalDate validade;

    public TransacaoParaRecebivel(String valorNaoDecimal, String metodoPagamento, String validadeNaoData) {
        Double valorConverter = Double.parseDouble(valorNaoDecimal);
        this.valor = BigDecimal.valueOf(valorConverter);
        this.metodoPagamento = metodoPagamento;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String.format(validadeNaoData, formatter);
        this.validade = LocalDate.parse(validadeNaoData, formatter);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public LocalDate getValidade() {
        return validade;
    }
}
