package ar.edu.utn.frba.dds.dtos.inputs;
import lombok.Data;

@Data
// de momento estos datos que son los que llegan al menos de encargarse de heladera
public class HeladeraInputDTO {
    private String nombre;
    private Integer idModelo;
    private String calle;
}
