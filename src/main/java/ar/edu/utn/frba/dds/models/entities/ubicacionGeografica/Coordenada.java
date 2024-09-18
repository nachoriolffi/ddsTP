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
    private Long id;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    public Coordenada(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public Coordenada(){

    }


}
