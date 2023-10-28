package br.estacionamento.dominio;

public class VeiculoCarga extends Veiculo {
    private Integer eixos;

    public VeiculoCarga() {

    }

    public VeiculoCarga(String placa, Integer eixos) {
        super(placa);
        this.eixos = eixos;
    }

    public Integer getEixos() {
        return eixos;
    }

    public void setEixos(Integer eixos) {
        this.eixos = eixos;
    }
}
