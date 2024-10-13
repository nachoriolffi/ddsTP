package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "respuesta")
public class Respuesta {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;

   @ManyToOne
   @JoinColumn(name = "cuestionarioRespondido_id")
   private CuestionarioRespondido cuestionarioRespondido;

    @Column(name = "respuestaAbierta", columnDefinition = "TEXT")
    private String respuestaAbierta;

    @ManyToMany
    @JoinTable(
            name = "respuesta_opcion",
            joinColumns = @JoinColumn(name = "respuesta_id"),
            inverseJoinColumns = @JoinColumn(name = "opcion_id")
    )
    private List<Opcion> opciones;

    public Respuesta(Pregunta pregunta,String r123espuestaAbierta){
        this.pregunta = pregunta;
        this.respuestaAbierta = respuestaAbierta;
    }

    public Respuesta(){}

    public boolean esObligatoria(){
        return pregunta.esObligatoria();
    }

}
