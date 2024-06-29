package ar.edu.utn.frba.dds.models.entities.vulnerable;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Local;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class Vulnerable {

    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeRegistro;
    private Boolean situacionDeCalle;
    private Direccion direccion;
    private TipoDocumento tipoDocumento;
    private Integer numeroDocumento;
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
