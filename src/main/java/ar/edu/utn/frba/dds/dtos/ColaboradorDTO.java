package ar.edu.utn.frba.dds.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ColaboradorDTO {
    private String ID;
    private String nombreYApellido;
    private String tipoColaborador;
    private String fechaDeNacimiento;
    private String razonSocial;
    private String jurisdiccion;
    private String direccion;
}
