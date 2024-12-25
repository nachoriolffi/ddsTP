package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "cuestionario_respondido")
public class CuestionarioRespondido {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuestionario_id")
    private Cuestionario cuestionario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "cuestionarioRespondido")
    private List<Respuesta> respuestas;

    public CuestionarioRespondido() {
        this.respuestas = new ArrayList<Respuesta>();
    }

    public void agregarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
       respuesta.setCuestionarioRespondido(this);
    }

    public void quitarRespuesta(Respuesta respuesta) {
        respuestas.remove(respuesta);
       respuesta.setCuestionarioRespondido(null);
    }

    public CuestionarioRespondido(Cuestionario cuestionario){
        this.cuestionario= cuestionario;
        this.respuestas= new ArrayList<Respuesta>();
    }

}
