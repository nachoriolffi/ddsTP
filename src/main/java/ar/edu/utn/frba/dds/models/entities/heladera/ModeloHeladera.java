package ar.edu.utn.frba.dds.models.entities.heladera;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModeloHeladera {

    private Double temperaturaMaxima;
    private Double temperaturaMinima;
    private Double peso;
    private Integer cantidadMaximaDeViandas;

    public ModeloHeladera(Double temperaturaMaxima, Double temperaturaMinima, Double peso, Integer cantidadMaximaDeViandas) {
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.peso = peso;
        this.cantidadMaximaDeViandas = cantidadMaximaDeViandas;
    }
}
