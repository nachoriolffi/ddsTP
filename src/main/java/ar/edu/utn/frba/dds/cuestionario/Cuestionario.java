package ar.edu.utn.frba.dds.cuestionario;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cuestionario {
        private String nombreCuestionario;
        private String descripcion;
        private List<Pregunta> preguntas;

        public void agregarPregunta(Pregunta pregunta) {
            preguntas.add(pregunta);

        }

        public void quitarPregunta(Pregunta pregunta) {
            preguntas.remove(pregunta);

        }
}
