package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoOutputDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer DNI;
    private Integer CUIL;
    private Integer areaCobertura;
}
