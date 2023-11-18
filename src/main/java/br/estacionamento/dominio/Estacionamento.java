package br.estacionamento.dominio;

import br.estacionamento.dominio.calculos.CalculoVeiculoCarga;
import br.estacionamento.dominio.calculos.CalculoVeiculoComum;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Estacionamento {
    private EntityManagerFactory factory;

    //private List<Ticket> tickets = new LinkedList<>();

    public void setUp() {
        factory = Persistence
                .createEntityManagerFactory("Estacionamento-PU")
    }

    public Ticket registrarEntrada(String placa) {
        var em = factory.createEntityManager();

        //var id = tickets.size();
        var dataEntrada = LocalDateTime.now();

        var veiculo = em.find(Veiculo.class, placa);

        if(veiculo == null)
                veiculo = new Veiculo(placa);

        var ticket = new Ticket(null, dataEntrada, veiculo);

        //tickets.add(ticket);
        em.getTransaction().begin();
        em.persist(ticket);
        em.getTransaction().commit();
        em.close();

        //em.getTransaction().rollback();

        return ticket;
    }

    public Ticket registrarSaida(Integer codigo) {
        /*
        var ticket = tickets.stream()
                            .filter(t -> t.getCodigo() == codigo)
                            .findFirst()
                            .get();
         */

        var em = factory.createEntityManager();

        var ticket = em.find(Ticket.class, codigo);

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

        em.getTransaction().begin();
        em.merge(ticket);
        em.getTransaction().commit();
        em.close();

        return ticket;
    }

    public void cancelarTicket(Integer codigo) {
        var em = factory.createEntityManager();

        var ticket = em.find(Ticket.class, codigo);

        em.getTransaction().begin();
        em.remove(ticket);
        em.getTransaction().commit();

        em.close();
    }

    public void tearDown() {
        factory.close();
    }
}
