package br.estacionamento.dominio.calculos;

public class CalculoVeiculoComum
        implements CalculoStrategy {

    @Override
    public double calcular(Long horas) {
        if(horas < 12) {
            return horas * 2.0;
        } else if(horas < 24) {
            return 50.0;
        } else if(horas < 24 * 15) {
            return horas / 24 * 50.0;
        } else {
            return 250.0;
        }
    }
}
