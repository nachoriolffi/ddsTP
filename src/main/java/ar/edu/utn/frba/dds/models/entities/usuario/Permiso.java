package ar.edu.utn.frba.dds.models.entities.usuario;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "permiso")
public class Permiso {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "descripcion",columnDefinition = "VARCHAR(255")
    private String descripcion;

}
