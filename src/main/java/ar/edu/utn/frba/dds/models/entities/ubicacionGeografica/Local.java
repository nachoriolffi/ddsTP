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

    @Column(name = "nombreLocal", nullable = false, columnDefinition = "VARCHAR(255)")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "direccion_id", nullable = false)
    private Direccion direccion;
}
