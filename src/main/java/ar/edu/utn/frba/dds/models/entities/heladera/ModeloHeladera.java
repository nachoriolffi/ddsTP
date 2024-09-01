package ar.edu.utn.frba.dds.models.entities.heladera;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Modelo_Heladera")
public class ModeloHeladera {

    @Id
    @GeneratedValue ( strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id_ModeloHeladera;

    @Column(name = "temperaturaMaxima", columnDefinition = "DOUBLE")
    private Double temperaturaMaxima;
    @Column(name = "temperaturaMinima", columnDefinition = "DOUBLE")
    private Double temperaturaMinima;
    @Column(name = "peso", columnDefinition = "DOUBLE")
    private Double peso;
    @Column(name = "cantidadMaximaDeViandas", columnDefinition = "INT")
    private Integer cantidadMaximaDeViandas;

    public ModeloHeladera(Double temperaturaMaxima, Double temperaturaMinima, Double peso, Integer cantidadMaximaDeViandas) {
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.peso = peso;
        this.cantidadMaximaDeViandas = cantidadMaximaDeViandas;
    }
}
