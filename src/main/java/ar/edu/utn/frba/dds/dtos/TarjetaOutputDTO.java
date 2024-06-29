package ar.edu.utn.frba.dds.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TarjetaOutputDTO {
    private Integer idTarjeta;
    private Date fechaRegistro;

    private List<Long> idRegistroUsos;
    private Long idPersonaAsociada;
    private Long idColaboradorAsociado;
}
