package ar.edu.utn.frba.dds.models.entities.cuestionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Opcion {

    private String texto;
    private boolean esCorrecta;

    public Opcion(String texto, boolean esCorrecta) {
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }
}
