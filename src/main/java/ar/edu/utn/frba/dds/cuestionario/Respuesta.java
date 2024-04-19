package ar.edu.utn.frba.dds.cuestionario;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Respuesta {

    private Pregunta pregunta;
    private List<Opcion> opcion;

    private String respuestaAbierta;

    public Respuesta() {
        this.pregunta =new Pregunta();
        this.opcion = new ArrayList<Opcion>();
        this.respuestaAbierta = respuestaAbierta;
    }
}
