package ar.edu.utn.frba.dds.cuestionario;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Respuesta {

    private Pregunta pregunta;
    private List<Opcion> opcion;

    private String respuestaAbierta;

    public Respuesta(Pregunta pregunta,String respuestaAbierta){
        this.pregunta = pregunta;
        this.opcion = new ArrayList<Opcion>();
        this.respuestaAbierta = respuestaAbierta;
    }

    public boolean esObligatoria(){
        return pregunta.esObligatoria();
    }

    public void agregarOpcion(Opcion opcion){
        this.opcion.add(opcion);
    }

}
