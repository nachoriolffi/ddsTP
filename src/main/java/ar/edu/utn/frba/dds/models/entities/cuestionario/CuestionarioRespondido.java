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
    @GeneratedValue ( strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id_CuestionarioRespondido;

    @ManyToOne
    @JoinColumn(name = "id_cuestionario")
    private Cuestionario cuestionario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_respuesta")
    private List<Respuesta> respuestas;

    public void agregarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    public CuestionarioRespondido(Cuestionario cuestionario){
        this.cuestionario= cuestionario;
        this.respuestas= new ArrayList<Respuesta>();
    }

}
