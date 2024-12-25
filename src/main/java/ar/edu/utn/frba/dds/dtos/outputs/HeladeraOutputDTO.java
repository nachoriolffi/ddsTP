package ar.edu.utn.frba.dds.dtos.outputs;

import lombok.Data;

@Data
public class HeladeraOutputDTO {
    private String id;
    private Double latitud;
    private Double longitud;
    private String nombre;
    private String direccion;
    private Integer capacidad;
    private Integer lugarDisponible;
    private String viandasRestantes;
    private String fechaPuestaFunc;
}
