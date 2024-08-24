package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import javax.persistence.*;

@Entity
@Table(name = "rubro")
public class Rubro {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(25)")
    String nombre;


    public Rubro(String nombre) {
        this.nombre = nombre;
    }
}
