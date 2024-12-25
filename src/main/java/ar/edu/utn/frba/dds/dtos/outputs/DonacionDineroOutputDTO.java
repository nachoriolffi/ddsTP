package ar.edu.utn.frba.dds.dtos.outputs;

import lombok.Data;

@Data
public class DonacionDineroOutputDTO {
    Long id;
    String fechaDonacion;
    String monto;
    String esPeriodica;
    Boolean renovacion;
}
