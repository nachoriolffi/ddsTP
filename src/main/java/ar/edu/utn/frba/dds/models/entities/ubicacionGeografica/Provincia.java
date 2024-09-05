package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "provincia")
public class Provincia {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Provincia;
    @Getter
    @Column(name = "provincia")
    private String provincia;

}
