package ar.edu.utn.frba.dds.dtos;

import lombok.Data;

@Data
public class VulnerableDTO {
    private String nombre;
    private String apellido;
    private String fechaDeNacimiento;
    private String situacionDeCalle;
    private String calle;
    private Integer altura;
    private Integer piso;
    private String provincia; // falta setear en el factory
    private String municipio;// falta setear en el factory
    private String localidad;// falta setear en el factory
    private String TipoDocumento;
    private Integer numeroDocumento;
    private Integer personasACargo;
    private Boolean tienePersonasACargo;
}