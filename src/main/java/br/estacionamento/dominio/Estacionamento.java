package br.estacionamento.dominio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Estacionamento {
    private List<Ticket> tickets = new LinkedList<>();
    public Ticket registrarEntrada(String placa) {
        var id = tickets.size();
        var dataEntrada = LocalDateTime.now();
        var veiculo = new Veiculo(placa);

        var ticket = new Ticket(id, dataEntrada, veiculo);

        tickets.add(ticket);

        return ticket;
    }

    public Ticket registrarSaida(Integer codigo) {

    }
}
