package ar.edu.utn.frba.dds.dtos.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenteInputDTO {
    private Integer id;
    private Integer tipoIncidente;
    private Integer tipoAlerta;
    private String descripcion;
    private String pathFoto;
}
