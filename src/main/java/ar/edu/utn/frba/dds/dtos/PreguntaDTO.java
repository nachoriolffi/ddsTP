package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaDTO {
    private Long id;
    private String nombre;
    private Boolean esObligatoria;
    private String tipoPregunta; // Changed to String
    //private List<Opcion> opciones;

    public PreguntaDTO (Pregunta pregunta) {
        this.setId(pregunta.getId());
        this.setNombre(pregunta.getNombre());
        this.setEsObligatoria(pregunta.getEsObligatoria());
        this.setTipoPregunta(pregunta.getTipoPregunta());
        //this.setOpciones(pregunta.getOpciones());

    }
}
