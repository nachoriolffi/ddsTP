package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "municipio")
public class Municipio {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "municipio")
    private String municipio;

    @Setter
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
}
