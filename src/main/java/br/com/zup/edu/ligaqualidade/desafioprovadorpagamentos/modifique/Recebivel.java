package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Recebivel {
    private String metodoPagamento;
    private LocalDate validade;
    private BigDecimal valor;


    public Recebivel(String metodoPagamento, LocalDate validade, BigDecimal valor) {
        this.metodoPagamento = metodoPagamento;
        this.valor = valor;
        this.validade = validade;
    }


    public void prazoRecebimento() {

    }
}
