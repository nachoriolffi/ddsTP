
package ar.edu.utn.frba.dds.dtos.outputs;

import lombok.Data;

@Data
public class DonacionViandaOutputDTO {
    private String comida;
    private String calorias;
    private String pesoEnGramos;
    private String fechaDonacion;
    private String fechaCaducidad;
    private String heladera;
    private String fueEntregada;
}
