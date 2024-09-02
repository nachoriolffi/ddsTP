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
    private Long id_CuestionarioRespondido;

    @ManyToOne
    @JoinColumn(name = "id_Cuestionario")
    private Cuestionario cuestionario;

    @OneToMany(mappedBy = "cuestionarioRespondido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    public CuestionarioRespondido() {
        this.respuestas = new ArrayList<Respuesta>();
    }

    public void agregarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    public CuestionarioRespondido(Cuestionario cuestionario){
        this.cuestionario= cuestionario;
        this.respuestas= new ArrayList<Respuesta>();
    }

}
