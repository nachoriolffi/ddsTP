package ar.edu.utn.frba.dds.dtos.outputs;

import lombok.Data;

@Data
public class DistribucionViandaOutputDTO {
    private String heladeraOrigen;
    private String heladeraDestino;
    private String viandasMovidas;
    private String motivo;
    private String fechaDistribucion;
}
