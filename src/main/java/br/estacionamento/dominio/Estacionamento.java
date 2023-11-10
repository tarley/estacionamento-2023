package br.estacionamento.dominio;

import br.estacionamento.dominio.calculos.CalculoVeiculoCarga;
import br.estacionamento.dominio.calculos.CalculoVeiculoComum;

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

        var ticket = tickets.stream()
                            .filter(t -> t.getCodigo() == codigo)
                            .findFirst()
                            .get();

        ticket.setSaida(LocalDateTime.now());

        if(ticket.getVeiculo() instanceof Veiculo)
            ticket.setCalculo(new CalculoVeiculoComum());
        else if(ticket.getVeiculo() instanceof VeiculoCarga) {
            var vehCarga =  (VeiculoCarga)
                    ticket.getVeiculo();
            ticket.setCalculo(
                    new CalculoVeiculoCarga(
                           vehCarga.getEixos()
                    )
            );
        }

        return ticket;
    }
}
