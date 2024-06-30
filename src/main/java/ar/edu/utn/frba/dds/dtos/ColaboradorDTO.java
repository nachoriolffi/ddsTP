package ar.edu.utn.frba.dds.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ColaboradorDTO {
    private String nombre;
    private String apellido;
    private String fechaDeNacimiento;
    private String calle;
    private Integer altura;
    private Integer piso;
    //private String provincia; // falta setear en el factory
    //private String municipio; // falta setear en el factory
    //private String localidad; // falta setear en el factory
    private String tipoDocumento;
    private Integer numeroDocumento;
    private Integer razonSocial;
    private String rubro;
    private String tipoPersona;
    private String tipoJuridisccion;
    private List<String> formasDeColaboracion; // son los enums
    //private List<String> mediosDeComunicacion;
    //private CuestionarioRespondido cuestionarioRespondido;
    //private Contacto contacto;
}
