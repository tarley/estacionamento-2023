package br.estacionamento.dominio.calculos;

public class CalculoVeiculoCarga
    implements CalculoStrategy {

    private Integer eixos;

    public CalculoVeiculoCarga(Integer eixos) {
        this.eixos = eixos;
    }

    @Override
    public double calcular(Long horas) {
        if(horas < 12) {
            return horas * 2.0 * eixos;
        } else if(horas < 24) {
            return 150.0;
        } else if(horas < 24 * 15) {
            return horas / 24 * 50.0 * eixos;
        } else {
            return 1500.0;
        }
    }
}
