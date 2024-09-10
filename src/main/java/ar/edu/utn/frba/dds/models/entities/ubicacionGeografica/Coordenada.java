package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "coordenada")
@Entity
public class  Coordenada {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_Coordenada;
    @Column(name = "latitud",columnDefinition = "DOUBLE")
    private Double latitud;
    @Column(name = "longitud",columnDefinition = "DOUBLE")
    private Double longitud;

    public Coordenada(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public Coordenada(){

    }


}
