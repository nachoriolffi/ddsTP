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
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoPregunta", nullable = false)
    private TipoPregunta tipoPregunta;

    public Pregunta( String nombre, Boolean esObligatoria, TipoPregunta tipoPregunta) {
        this.nombre = nombre;
        this.esObligatoria = esObligatoria;
        this.tipoPregunta = tipoPregunta;
    }

    public boolean esObligatoria(){
            return this.esObligatoria;
        }


}
