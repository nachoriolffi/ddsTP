package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;

public class RepoRespuesta extends RepoGenerico<Respuesta> {
    public static RepoRespuesta INSTANCE = new RepoRespuesta();

    public RepoRespuesta() {
        super(Respuesta.class);
    }
}
