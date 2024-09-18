package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "calle")
public class Calle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calle")
    @Setter
    private String calle;

    public Calle(String calle) {
        this.calle = calle;
    }

    public Calle() {

    }
}
