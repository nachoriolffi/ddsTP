package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;

public class RepoPregunta extends RepoGenerico<Pregunta>  {

    public static RepoPregunta INSTANCE = new RepoPregunta();
    public RepoPregunta() {
        super(Pregunta.class);
    }
}
