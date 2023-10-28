package br.estacionamento.dominio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private Integer codigo;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private Veiculo veiculo;

    public Ticket() {
    }

    public Ticket(Integer codigo, LocalDateTime entrada, Veiculo veiculo) {
        this.codigo = codigo;
        this.entrada = entrada;
        this.veiculo = veiculo;
    }

    public long getHoras() {
        return Duration.between(entrada, saida)
                .toHours();
    }

    public double getValor() {
        var horas = getHoras();

        if(veiculo instanceof Veiculo) {
            if(horas < 12) {
                return horas * 2.0;
            } else if(horas < 24) {
                return 50.0;
            } else if(horas < 24 * 15) {
                return horas / 24 * 50.0;
            } else {
                return 250.0;
            }
        } else if(veiculo instanceof VeiculoCarga) {

        }

        return 0.0;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(codigo, ticket.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "codigo=" + codigo +
                ", entrada=" + entrada +
                ", saida=" + saida +
                ", veiculo=" + veiculo +
                '}';
    }
}