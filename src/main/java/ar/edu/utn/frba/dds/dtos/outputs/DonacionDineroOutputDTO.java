package ar.edu.utn.frba.dds.dtos.outputs;

import lombok.Data;

@Data
public class DonacionDineroOutputDTO {
    String fechaDonacion;
    String monto;
    String esPeriodica;
}
