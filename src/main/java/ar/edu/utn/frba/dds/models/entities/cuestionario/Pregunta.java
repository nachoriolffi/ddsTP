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
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombrePregunta", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @Column(name = "esObligatoria")
    private Boolean esObligatoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoPregunta", nullable = false)
    private TipoPregunta tipoPregunta;

    public Pregunta( String nombre, Boolean esObligatoria, TipoPregunta tipoPregunta) {
        this.nombre = nombre;
        this.esObligatoria = esObligatoria;
        this.tipoPregunta = tipoPregunta;
    }

    public Pregunta() {

    }

    public boolean esObligatoria(){
            return this.esObligatoria;
        }


}
