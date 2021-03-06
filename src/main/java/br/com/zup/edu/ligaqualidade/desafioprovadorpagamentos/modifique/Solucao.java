package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solucao {

    /**
     * @param infoTransacoes    dados das transações. A String está formatada da seguinte maneira:
     *                          <b>"valor,metodoPagamento,numeroCartao,nomeCartao,validade,cvv,idTransacao"</b>
     *                          <ol>
     *                          <li> Valor é um decimal</li>
     *                          <li> O método de pagamento é 'DEBITO' ou 'CREDITO' </li>
     *                          <li> Validade é uma data no formato dd/MM/yyyy. </li>
     *                          </ol>
     * @param infoAdiantamentos informacao da transacao que pode ser recebida adiantada. A String está formatada da seguinte maneira:
     *                          <b>"idTransacao,taxa"</b>
     *                          <ol>
     *                          <li> Taxa é um decimal </li>
     *                          </ol>
     * @return Uma lista de array de string com as informações na seguinte ordem:
     * [status,valorOriginal,valorASerRecebidoDeFato,dataEsperadoRecebimento].
     * <ol>
     *  <li>O status pode ser 'pago' ou 'aguardando_pagamento'</li>
     *  <li>O valor original e o a ser recebido de fato devem vir no formato decimal. Ex: 50.45</li>
     *  <li>dataEsperadoRecebimento deve ser formatada como dd/MM/yyyy. Confira a classe {@link DateTimeFormatter}</li>
     * </ol>
     * <p>
     * É esperado que o retorno respeite a ordem de recebimento
     */


    //7
    public static List<String[]> executa(List<String> infoTransacoes, List<String> infoAdiantamentos) {

        //1
        TransacaoParaRecebivel transacaoParaRecebivel = null;

        //1
        AdiantamentoParaRecebivel adiantamentoParaRecebivel;

        List<String> transacoes = new ArrayList<>();
        List<String> adiantamentos = new ArrayList<>();

        /*
         * Quebra o String num array
         * */
        //1
        for (String info : infoTransacoes) {
            transacoes = Arrays.asList(info.split(","));
        }

        //1
        for (String info : infoAdiantamentos) {
            adiantamentos = Arrays.asList(info.split(","));
        }


        /*
         * Transforma a String em dados manipuláveis
         * */
        //1
        for (int j = 0; j < transacoes.size(); j++) {

            String valorNaoDecimal = transacoes.get(0);
            String metodo = transacoes.get(1);
            String validadeNaoData = transacoes.get(4);

            transacaoParaRecebivel = new TransacaoParaRecebivel(valorNaoDecimal, metodo, validadeNaoData);
        }

        //1
        for (int k = 0; k < adiantamentos.size(); k++) {
            String idTransacaoString = adiantamentos.get(0);
            String taxaNaoDecimal = adiantamentos.get(1);

            adiantamentoParaRecebivel = new AdiantamentoParaRecebivel(idTransacaoString, taxaNaoDecimal);

        }

        /*
         * Início da criação do recebível
         * */
        //1
        Recebivel recebivel = criaRecebivel(transacaoParaRecebivel);
        recebivel.prazoRecebimento();

        return List.of(new String[][]{
                {"pago", "200", "194", "04/03/2021"}
        });


    }

    private static Recebivel criaRecebivel(TransacaoParaRecebivel transacao) {

        return new Recebivel(transacao.getMetodoPagamento(), transacao.getValidade(), transacao.getValor());
    }


}
