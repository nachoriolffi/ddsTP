package ar.edu.utn.frba.dds.cuestionario;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CuestionarioRespondido {
    private Cuestionario cuestionario;
    private List<Respuesta> respuestas;

}
