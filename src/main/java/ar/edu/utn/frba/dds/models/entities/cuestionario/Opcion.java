package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "opcion")
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "texto", columnDefinition = "VARCHAR(255)")
    private String texto;
    @Column(name = "esCorrecta",columnDefinition = "BOOLEAN")
    private boolean esCorrecta;

    @ManyToMany(mappedBy = "opciones")
    private List<Respuesta> respuestas;

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;

    public Opcion() {
    }

    public Opcion(String texto) {
        this.texto = texto;
        this.respuestas = new ArrayList<>();
    }
}
