package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "local")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_local",nullable = false)
    private String nombre;
    @Transient // TODO
    private Direccion direccion;
}
