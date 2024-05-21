package ar.edu.utn.frba.dds.vulnerable;

import ar.edu.utn.frba.dds.colaborador.Direccion;
import ar.edu.utn.frba.dds.colaborador.EnumTipoDocumento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vulnerable {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private Date fechaDeRegistro;
    private Boolean situacionDeCalle;
    private ar.edu.utn.frba.dds.colaborador.Direccion Direccion;
    private EnumTipoDocumento tipoDocumento;
    private List<RegistroDePersonaACargo> registroDePersonasACargo;

    public Vulnerable(String nombre, String apellido, Date fechaDeNacimiento, Date fechaDeRegistro, Boolean situacionDeCalle, Direccion Direccion, EnumTipoDocumento tipoDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeRegistro = fechaDeRegistro;
        this.situacionDeCalle = situacionDeCalle;
        this.Direccion = Direccion;
        this.tipoDocumento = tipoDocumento;
        this.registroDePersonasACargo = new ArrayList<RegistroDePersonaACargo>();
    }




}
