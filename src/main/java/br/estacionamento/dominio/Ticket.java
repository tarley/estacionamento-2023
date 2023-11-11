package br.estacionamento.dominio;

import br.estacionamento.dominio.calculos.CalculoStrategy;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ticket {
    @Id
    private Integer codigo;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "num_placa")
    private Veiculo veiculo;

    @Transient
    private CalculoStrategy calculo;

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

        return calculo.calcular(horas);
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

    public CalculoStrategy getCalculo() {
        return calculo;
    }

    public void setCalculo(CalculoStrategy calculo) {
        this.calculo = calculo;
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