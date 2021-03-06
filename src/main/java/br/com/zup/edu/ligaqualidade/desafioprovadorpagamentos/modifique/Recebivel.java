package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Recebivel {
    private String status;
    private Double valorOriginal;
    private Double valorReceber;
    private LocalDate dtRecebimento;

    public Recebivel() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(Double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public Double getValorReceber() {
        return valorReceber;
    }

    public void setValorReceber(Double valorReceber) {
        this.valorReceber = valorReceber;
    }

    public LocalDate getDtRecebimento() {
        return dtRecebimento;
    }

    public void setDtRecebimento(LocalDate dtRecebimento) {
        this.dtRecebimento = dtRecebimento;
    }

    public String[] toStringArray() {
        return new String[]
                {this.getStatus(),
                        this.getValorOriginal().toString(),
                        this.getValorReceber().toString(),
                        this.getDtRecebimento()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))};
    }
}
