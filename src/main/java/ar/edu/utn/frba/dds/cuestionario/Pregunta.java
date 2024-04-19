package ar.edu.utn.frba.dds.cuestionario;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Pregunta {


        private String nombre;

        private List<Opcion> opciones;

        private Boolean esObligatoria;

        private tipoPregunta tipoPregunta;

        public Pregunta() {
            this.nombre = nombre;
            this.opciones = opciones;
            this.esObligatoria = esObligatoria;
            this.tipoPregunta = tipoPregunta;
        }



}
