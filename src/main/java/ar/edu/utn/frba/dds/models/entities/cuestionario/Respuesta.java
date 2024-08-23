package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table (name = "respuesta")
public class Respuesta {

    @Id
    @GeneratedValue ( strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idRespuesta;
    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;
    //private List<Opcion> opcion;
    @Column(name = "respuestaAbierta", columnDefinition = "TEXT")
    private String respuestaAbierta;

    public Respuesta(Pregunta pregunta,String respuestaAbierta){
        this.pregunta = pregunta;
        //this.opcion = new ArrayList<Opcion>();
        this.respuestaAbierta = respuestaAbierta;
    }

    public boolean esObligatoria(){
        return pregunta.esObligatoria();
    }

    //public void agregarOpcion(Opcion opcion){this.opcion.add(opcion);}

}
