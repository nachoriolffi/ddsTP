package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pregunta {


        private String nombre;

        //private List<Opcion> opciones;

        private Boolean esObligatoria;

        private TipoPregunta tipoPregunta;

        public Pregunta( String nombre, Boolean esObligatoria, TipoPregunta tipoPregunta) {
            this.nombre = nombre;
            //this.opciones = new ArrayList<Opcion>();
            this.esObligatoria = esObligatoria;
            this.tipoPregunta = tipoPregunta;
        }

       // public Pregunta() {this.opciones = new ArrayList<Opcion>();}

        public boolean esObligatoria(){
            return this.esObligatoria;
        }


}
