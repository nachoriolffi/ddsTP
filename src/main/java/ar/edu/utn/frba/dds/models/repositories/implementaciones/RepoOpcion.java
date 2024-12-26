package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Opcion;

public class RepoOpcion extends RepoGenerico<Opcion> {
    public static RepoOpcion INSTANCE = new RepoOpcion();

    public RepoOpcion() {
        super(Opcion.class);
    }
}
