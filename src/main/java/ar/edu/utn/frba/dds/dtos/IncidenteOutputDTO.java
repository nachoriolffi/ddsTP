package ar.edu.utn.frba.dds.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IncidenteOutputDTO {
    private Long id;
    private Integer tipoIncidente;
    private Integer tipoAlerta;
    private String descripcion;
    private String pathFoto;
}
