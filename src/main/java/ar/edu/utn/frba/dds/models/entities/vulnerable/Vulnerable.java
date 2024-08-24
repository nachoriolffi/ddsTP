package ar.edu.utn.frba.dds.models.entities.vulnerable;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Local;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private Long id;
    @Column (name = "nombre", columnDefinition = "VARCHAR(25)")
    private String nombre;
    @Column (name = "apellido", columnDefinition = "VARCHAR(25)")
    private String apellido;
    @Column (name = "fechaDeNacimiento", columnDefinition = "DATE")
    private LocalDate fechaDeNacimiento;
    @Column (name = "fechaDeRegistro", columnDefinition = "DATE")
    private LocalDate fechaDeRegistro;
    @Column (name = "situacionDeCalle", columnDefinition = "BOOLEAN")
    private Boolean situacionDeCalle;
    @Transient
    private Direccion direccion;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @Column(name = "numeroDocumento", columnDefinition = "INT")
    private Integer numeroDocumento;
    @Transient
    private List<RegistroDePersonaACargo> registroDePersonasACargo;


    public Vulnerable(){

    }

    public Vulnerable(String nombre, String apellido, LocalDate fechaDeNacimiento, LocalDate fechaDeRegistro, Boolean situacionDeCalle, Direccion direccion, TipoDocumento tipoDocumento,Integer numeroDoc) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeRegistro = fechaDeRegistro;
        this.situacionDeCalle = situacionDeCalle;
        this.direccion = direccion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDoc;
        this.registroDePersonasACargo = new ArrayList<RegistroDePersonaACargo>();
    }

    public void agregarregistroDePersonasACargo(RegistroDePersonaACargo registro) {
        registroDePersonasACargo.add(registro);
    }

}
