package ar.edu.utn.frba.dds.dtos.outputs;

import lombok.Data;

@Data
public class SuscripcionesAHeladerasOutputDTO {
    private String nombre;
    private String direccion;
    //Motivo
    private Integer cantidadDeViandas;
    //private Boolean notificación;
    //Heladeras sugeridas
    private Boolean Desuscribirme;
}
