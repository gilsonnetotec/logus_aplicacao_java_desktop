package resources;

import java.util.List;

public class CarsResource {
    private String modelo;
    private String placa;
    private List<String> tiposCombustivel;

    public CarsResource(String modelo, String placa, List<String> tiposCombustivel) {
        this.modelo = modelo;
        this.placa = placa;
        this.tiposCombustivel = tiposCombustivel;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public List<String> getTiposCombustivel() {
        return tiposCombustivel;
    }
}
