package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;

public class RepoFormaDeColaboracion extends RepoGenerico<FormaDeColaboracion> {

    public static RepoFormaDeColaboracion INSTANCE = new RepoFormaDeColaboracion();

    public RepoFormaDeColaboracion() {
        super(FormaDeColaboracion.class);
    }
}
