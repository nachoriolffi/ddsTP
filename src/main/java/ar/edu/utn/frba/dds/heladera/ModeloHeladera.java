package ar.edu.utn.frba.dds.heladera;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModeloHeladera {

    private Float temperaturaMaxima;
    private Float temperaturaMinima;
    private Double peso;
    private Integer cantidadMaximaDeViandas;


    public Boolean verificarTemperatura(Double ultimaTemp) {
        return ultimaTemp >= temperaturaMinima && ultimaTemp <= temperaturaMaxima;
    }

}
