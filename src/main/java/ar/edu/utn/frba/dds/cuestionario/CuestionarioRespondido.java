package ar.edu.utn.frba.dds.cuestionario;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CuestionarioRespondido {
    private Cuestionario cuestionario;
    private List<Respuesta> respuestas;

    public void agregarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    public CuestionarioRespondido(Cuestionario cuestionario){
        this.cuestionario= cuestionario;
        this.respuestas= new ArrayList<Respuesta>();
    }

}
