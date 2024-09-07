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
    @Column(name = "nombre_local",nullable = false, columnDefinition="VARCHAR(50)")
    private String nombre;
    @OneToOne
    @JoinColumn (name = "id_Direccion", referencedColumnName = "id", nullable = false)
    private Direccion direccion;
}
