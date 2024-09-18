package ar.edu.utn.frba.dds.models.entities.vulnerable;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vulnerable")
public class Vulnerable {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)", nullable = false)
    private String nombre;

    @Column(name = "apellido", columnDefinition = "VARCHAR(255)", nullable = false)
    private String apellido;

    @Column(name = "fechaDeNacimiento", nullable = false)
    private Date fechaDeNacimiento;

    @Column(name = "fechaDeRegistro", nullable = false)
    private Date fechaDeRegistro;

    @Column(name = "situacionDeCalle", nullable = false)
    private Boolean situacionDeCalle;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDocumento")
    private TipoDocumento tipoDocumento;

    @Column(name = "numeroDocumento")
    private Integer numeroDocumento;

    @OneToMany(mappedBy = "vulnerable", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<RegistroMenorACargo> menoresACargo = new ArrayList<>();

    public Vulnerable() {

    }

    public Vulnerable(String nombre, String apellido, Date fechaDeNacimiento, Date fechaDeRegistro, Boolean situacionDeCalle, Direccion direccion, TipoDocumento tipoDocumento, Integer numeroDoc) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeRegistro = fechaDeRegistro;
        this.situacionDeCalle = situacionDeCalle;
        this.direccion = direccion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDoc;
        this.menoresACargo = new ArrayList<>();
    }

    public void agregarregistroDePersonasACargo(RegistroMenorACargo registro) {
        menoresACargo.add(registro);
    }

}
