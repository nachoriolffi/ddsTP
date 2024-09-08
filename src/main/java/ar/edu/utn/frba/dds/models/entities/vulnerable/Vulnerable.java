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
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id_Vulnerable;
    @Column (name = "nombre", columnDefinition = "VARCHAR(25)",nullable = false)
    private String nombre;
    @Column (name = "apellido", columnDefinition = "VARCHAR(25)",nullable = false)
    private String apellido;
    @Column (name = "fechaDeNacimiento", columnDefinition = "DATE",nullable = false)
    private Date fechaDeNacimiento;
    @Column (name = "fechaDeRegistro", columnDefinition = "DATE",nullable = false)
    private Date fechaDeRegistro;
    @Column (name = "situacionDeCalle", columnDefinition = "BOOLEAN",nullable = false)
    private Boolean situacionDeCalle;
    @Transient
    private Direccion direccion;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @Column(name = "numeroDocumento", columnDefinition = "INT")
    private Integer numeroDocumento;

    @OneToMany(mappedBy = "vulnerable", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<RegistroMenorACargo> menoresACargo = new ArrayList<>();


    public Vulnerable(){

    }

    public Vulnerable(String nombre, String apellido, Date fechaDeNacimiento, Date fechaDeRegistro, Boolean situacionDeCalle, Direccion direccion, TipoDocumento tipoDocumento,Integer numeroDoc) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeRegistro = fechaDeRegistro;
        this.situacionDeCalle = situacionDeCalle;
        this.direccion = direccion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDoc;
        this.menoresACargo = new ArrayList<RegistroMenorACargo>();
    }

    public void agregarregistroDePersonasACargo(RegistroMenorACargo registro) {
        menoresACargo.add(registro);
    }

}
