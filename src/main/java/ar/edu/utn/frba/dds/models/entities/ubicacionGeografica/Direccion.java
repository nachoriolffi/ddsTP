package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @OneToOne// saco el @Transient porque es necesario para el alta del colaborador
    @JoinColumn(name = "calle_id")
    private Calle calle;

    @Column(name = "altura")
    @Setter
    private Integer altura;

    @Column(name = "piso")
    @Setter
    private Integer piso;

    @Embedded
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


    public Direccion() {

    }
}
