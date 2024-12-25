package ar.edu.utn.frba.dds.models.entities.heladera;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "modelo_heladera")
public class ModeloHeladera {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreModelo",columnDefinition = "VARCHAR(255)",unique = true)
    private String nombreModelo;

    @Column(name = "tempMax")
    private Double temperaturaMaxima;

    @Column(name = "tempMin")
    private Double temperaturaMinima;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "cantidadMaximaDeViandas")
    private Integer cantidadMaximaDeViandas;

    public ModeloHeladera() {

    }

    public ModeloHeladera(Double temperaturaMaxima, Double temperaturaMinima, Double peso, Integer cantidadMaximaDeViandas) {
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.peso = peso;
        this.cantidadMaximaDeViandas = cantidadMaximaDeViandas;
    }
}
