package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11)")
    private Integer id_Direccion;
    @Setter
    @OneToOne// saco el @Transient porque es necesario para el alta del colaborador
    @JoinColumn(name = "calle_id", nullable = false)
    private Calle calle;
    @Column(name = "altura", columnDefinition = "INT(11)", nullable = false)
    private Integer altura;
    @Column(name = "piso", columnDefinition = "INT(11)")
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
