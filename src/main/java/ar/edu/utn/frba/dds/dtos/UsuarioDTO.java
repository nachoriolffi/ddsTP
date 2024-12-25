package ar.edu.utn.frba.dds.dtos;

import lombok.Data;

@Data
public class UsuarioDTO {
    String nombre;
    String apellido;
    String email;
    String telefono;
    String direccion;
    String provincia;
    String localidad;
    String clave;
    String razonSocial;
    String tipoJurisdiccion;


}
