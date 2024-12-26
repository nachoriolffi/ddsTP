package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@Table(name = "provincia")
public class Provincia {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    public Provincia() {

    }
}
