package ar.edu.utn.frba.dds.vulnerable;

import ar.edu.utn.frba.dds.utils.Direccion;
import ar.edu.utn.frba.dds.utils.TipoDocumento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vulnerable {

    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private Date fechaDeRegistro;
    private Boolean situacionDeCalle;
    private Direccion direccion;
    private TipoDocumento tipoDocumento;
    private List<RegistroDePersonaACargo> registroDePersonasACargo;

    public Vulnerable(String nombre, String apellido, Date fechaDeNacimiento, Date fechaDeRegistro, Boolean situacionDeCalle, Direccion direccion, TipoDocumento tipoDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeRegistro = fechaDeRegistro;
        this.situacionDeCalle = situacionDeCalle;
        this.direccion = direccion;
        this.tipoDocumento = tipoDocumento;
        this.registroDePersonasACargo = new ArrayList<RegistroDePersonaACargo>();
    }


}
