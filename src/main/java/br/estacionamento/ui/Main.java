package br.estacionamento.ui;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        var factory = Persistence
                            .createEntityManagerFactory(
                                    "Estacionamento-PU"
                            );

        var em = factory.createEntityManager();



        em.close();
        factory.close();

    }
}
