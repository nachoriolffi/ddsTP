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
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id_Respuesta;
    @ManyToOne
    @JoinColumn(name = "id_Pregunta")
    private Pregunta pregunta;


    @ManyToOne
    @JoinColumn(name = "id_CuestionarioRespondido")
    private CuestionarioRespondido cuestionarioRespondido;

    //private List<Opcion> opcion;
    @Column(name = "respuestaAbierta", columnDefinition = "TEXT")
    private String respuestaAbierta;

    public Respuesta(Pregunta pregunta,String r123espuestaAbierta){
        this.pregunta = pregunta;
        //this.opcion = new ArrayList<Opcion>();
        this.respuestaAbierta = respuestaAbierta;
    }

    public Respuesta(){}

    public boolean esObligatoria(){
        return pregunta.esObligatoria();
    }

    //public void agregarOpcion(Opcion opcion){this.opcion.add(opcion);}

}
