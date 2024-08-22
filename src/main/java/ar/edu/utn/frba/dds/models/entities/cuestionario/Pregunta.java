package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="pregunta")
public class Pregunta {

    @Id
    @GeneratedValue
    private Integer idPregunta;
    @Column(name = "nombrePregunta", columnDefinition = "VARCHAR(250)")
    private String nombre;

        //private List<Opcion> opciones;
    @Column(name = "esObligatoria",columnDefinition = "TINYINT(1)")
    private Boolean esObligatoria;
    @Column(name = "tipoPregunta")
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
