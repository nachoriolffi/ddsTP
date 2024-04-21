package ar.edu.utn.frba.dds.cuestionario;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class Pregunta {


        private String nombre;

        private List<Opcion> opciones;

        private Boolean esObligatoria;

        private tipoPregunta tipoPregunta;

        public Pregunta( String nombre, List<Opcion> opciones, Boolean esObligatoria, tipoPregunta tipoPregunta) {
            this.nombre = nombre;
            this.opciones = opciones;
            this.esObligatoria = esObligatoria;
            this.tipoPregunta = tipoPregunta;
        }

        public Pregunta() {

            this.opciones = new ArrayList<Opcion>();

        }

        public boolean esObligatoria(){
            return esObligatoria;
        }







}
