package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;

public class AdiantamentoParaRecebivel {

    private Long idTransacao;
    private BigDecimal taxa;

    public AdiantamentoParaRecebivel(String idTransacaoString, String taxaNaoDecimal) {
        this.idTransacao = Long.parseLong(idTransacaoString);
        Double taxaConverter = Double.parseDouble(taxaNaoDecimal);
        this.taxa = BigDecimal.valueOf(taxaConverter);
    }
}
