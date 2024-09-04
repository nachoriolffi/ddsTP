package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "provincia")
public class Provincia {

    @Id
    @Column(name = "id_Provincia")
    private Long id_Provincia;
    @Column(name = "provincia")
    private String provincia;

}
