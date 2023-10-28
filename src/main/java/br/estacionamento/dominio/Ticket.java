package br.estacionamento.dominio;

import java.time.LocalDateTime;

public class Ticket {
    private Integer codigo;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private Veiculo veiculo;
}
