package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "localidad")
public class Localidad {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "localidad")
    private String localidad;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
}
