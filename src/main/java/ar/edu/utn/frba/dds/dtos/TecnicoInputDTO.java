package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoInputDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer DNI;
    private Integer CUIL;
    public Integer areaCobertura;
}
