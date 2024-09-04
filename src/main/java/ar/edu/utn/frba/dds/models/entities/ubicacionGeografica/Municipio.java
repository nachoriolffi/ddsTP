package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "municipio")
public class Municipio {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_Municipio;

    @Column(name = "municipio")
    private String municipio;

    @Setter
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Provincia", referencedColumnName = "id_Provincia")
    private Provincia localidades;
}
