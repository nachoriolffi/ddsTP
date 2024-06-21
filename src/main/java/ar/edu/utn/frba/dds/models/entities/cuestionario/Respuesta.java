package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Respuesta {

    private Pregunta pregunta;
    //private List<Opcion> opcion;

    private String respuestaAbierta;

    public Respuesta(Pregunta pregunta,String respuestaAbierta){
        this.pregunta = pregunta;
        //this.opcion = new ArrayList<Opcion>();
        this.respuestaAbierta = respuestaAbierta;
    }

    public boolean esObligatoria(){
        return pregunta.esObligatoria();
    }

    //public void agregarOpcion(Opcion opcion){this.opcion.add(opcion);}

}
