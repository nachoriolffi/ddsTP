package ar.edu.utn.frba.dds.dtos;

import lombok.Data;

@Data
public class OfertaDTO {
    Long id;
    String oferta;
    Long cantidadPuntos;
    String fecha;
    String rubro;

}
