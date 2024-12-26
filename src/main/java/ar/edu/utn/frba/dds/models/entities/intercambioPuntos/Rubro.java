package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "rubro")
@Entity
@Setter
@Getter
public class Rubro {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}
