package ar.edu.utn.frba.dds.dtos.inputs;
import lombok.Data;

@Data
// de momento estos datos que son los que llegan al menos de encargarse de heladera
public class HeladeraInputDTO {
    private String nombre;
    private String modelo;
    private String calle;
    private String latitud;
    private String longitud;
    private String altura;
    private String piso;
}
