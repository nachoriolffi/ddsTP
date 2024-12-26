package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calle_id")
    private Calle calle;

    @Column(name = "altura")
    @Setter
    private Integer altura;

    @Column(name = "piso")
    @Setter
    private Integer piso;

    @Setter
    @Embedded
    private Ubicacion ubicacion;

    public Direccion() {

    }
}
