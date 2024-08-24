package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id_Direccion;
    @Setter
    @Transient
    private Calle calle;
    @Column(name = "altura")
    private Integer altura;
    @Column(name = "piso")
    private Integer piso;
    @Transient
    private Ubicacion ubicacion;

    public Direccion(String calle, Integer altura, Integer piso) {
        this.calle = new Calle(calle);
    }
    public Direccion(String calle, Integer altura, Integer piso, Ubicacion ubicacion) {
        this.calle = new Calle(calle);
        this.altura = altura;
        this.piso = piso;
        this.ubicacion = ubicacion;
    }


}
