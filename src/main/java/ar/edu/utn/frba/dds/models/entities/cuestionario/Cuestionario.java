package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "cuestionario")
public class Cuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreCuestionario", columnDefinition = "VARCHAR(255)")
    @Setter
    private String nombreCuestionario;

    @Column(name = "descripcion", columnDefinition = "VARCHAR(255)")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuestionario_id")
    private List<Pregunta> preguntas;

    public Cuestionario(String nombreCuestionario, String descripcion) {
        this.nombreCuestionario = nombreCuestionario;
        this.descripcion = descripcion;
        this.preguntas = new ArrayList<Pregunta>();
    }

    public Cuestionario() {
        this.preguntas = new ArrayList<Pregunta>();
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    public void quitarPregunta(Pregunta pregunta) {
        preguntas.remove(pregunta);
    }
}
