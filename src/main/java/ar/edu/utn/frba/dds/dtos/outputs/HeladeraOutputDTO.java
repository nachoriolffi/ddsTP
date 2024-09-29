package ar.edu.utn.frba.dds.dtos.outputs;

import lombok.Data;

@Data
public class HeladeraOutputDTO {
    private String nombre;
    private String direccion;
    private Integer capacidad;
    private Integer viandasRestantes;
    private String inicioOperaciones;
}
