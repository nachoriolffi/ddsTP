package ar.edu.utn.frba.dds.dtos.outputs;

import lombok.Data;

@Data
public class SuscripcionesAHeladerasOutputDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String motivo;
    private Integer cantidadDeViandas;
    private Boolean desperfecto;
}
