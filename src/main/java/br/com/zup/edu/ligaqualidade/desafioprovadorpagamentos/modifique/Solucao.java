package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static List<String[]> executa(List<String> infoTransacoes, List<String> infoAdiantamentos) {
        List<Transacao> transacoes = retornaTransacoes(infoTransacoes);
        List<AdiantamentoRecebivel> adiantamentoRecebiveis = retornaAdiantamentoRecebiveis(infoAdiantamentos);


        List<Recebivel> recebiveis = new LinkedList<>();
        for (Transacao transacao : transacoes) {
            Recebivel recebivel = criaRecebivel(transacao);
            calculaTaxaAdiantamento(recebivel, transacao.getIdTransacao(), adiantamentoRecebiveis);

            recebiveis.add(recebivel);
        }

        return recebiveis
                .stream()
                .map(Recebivel::toStringArray)
                .collect(Collectors.toList());
    }

    private static List<AdiantamentoRecebivel> retornaAdiantamentoRecebiveis(List<String> infoAdiantamentos) {
        List<AdiantamentoRecebivel> adiantamentoRecebiveis = new LinkedList<>();

        for (String adiantamentoStr : infoAdiantamentos) {
            String[] adiantamentoAtual = adiantamentoStr.split(",");

            adiantamentoRecebiveis.add(
                    new AdiantamentoRecebivel(Integer.parseInt(adiantamentoAtual[0]),
                            Double.parseDouble(adiantamentoAtual[1]))
            );
        }

        return adiantamentoRecebiveis;
    }

    private static List<Transacao> retornaTransacoes(List<String> infoTransacoes) {
        List<Transacao> transacoes = new LinkedList<>();

        for (String transacaoStr : infoTransacoes) {
            String[] transacaoAtual = transacaoStr.split(",");

            transacoes.add(new Transacao(
                   Double.parseDouble(transacaoAtual[0]),
                   transacaoAtual[1],
                   transacaoAtual[2],
                   transacaoAtual[3],
                    LocalDate.parse(transacaoAtual[4], DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                   Integer.parseInt(transacaoAtual[5]),
                   Integer.parseInt(transacaoAtual[6])
            ));
        }

        return transacoes;
    }

    private static Recebivel criaRecebivel(Transacao transacao) {
        Recebivel recebivel = new Recebivel();
        recebivel.setValorOriginal(transacao.getValor());

        if ("DEBITO".equals(transacao.getMetodoPagamento())) {
            recebivel.setStatus("pago");
            recebivel.setDtRecebimento(LocalDate.now());
            recebivel.setValorReceber(transacao.getValor() - (transacao.getValor() * 0.03));
        } else {
            recebivel.setStatus("aguardando_pagamento");
            recebivel.setDtRecebimento(LocalDate.now().plusDays(30));
            recebivel.setValorReceber(transacao.getValor() - (transacao.getValor() * 0.05));
        }

        return recebivel;
    }

    private static void calculaTaxaAdiantamento(Recebivel recebivel, Integer transacaoId, List<AdiantamentoRecebivel> adiantamentoRecebiveis) {
        adiantamentoRecebiveis.stream()
                .filter(adiantamentoRecebivel -> adiantamentoRecebivel.getIdTransacao()
                        .equals(transacaoId)).forEach(adiantamentoRecebivel -> {
                            recebivel.setValorReceber(recebivel.getValorReceber()
                                    - (recebivel.getValorReceber() * adiantamentoRecebivel.getTaxa()));
                            recebivel.setDtRecebimento(LocalDate.now());
        });
    }
}
