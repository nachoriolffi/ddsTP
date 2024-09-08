package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import javax.persistence.*;

@Entity
@Table(name = "calle")
public class Calle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11)")
    private Long id;
    @Column(name = "calle")
    private String calle;

    public Calle(String calle) {
        this.calle = calle;
    }

    public Calle() {

    }
}
