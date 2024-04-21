package ar.edu.utn.frba.dds.cuestionario;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cuestionario {
        private String nombreCuestionario;
        private String descripcion;
        private List<Pregunta> preguntas;

        public Cuestionario(String nombreCuestionario,String descripcion) {
            this.nombreCuestionario = nombreCuestionario;
            this.descripcion = descripcion;
            this.preguntas = new ArrayList<Pregunta>();
        }

        public void agregarPregunta(Pregunta pregunta) {
            preguntas.add(pregunta);

        }

        public void quitarPregunta(Pregunta pregunta) {
            preguntas.remove(pregunta);

        }
}
