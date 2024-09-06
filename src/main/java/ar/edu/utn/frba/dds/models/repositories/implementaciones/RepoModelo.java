package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
public class RepoModelo extends RepoGenerico<ModeloHeladera>{
    public static RepoModelo INSTANCE = new RepoModelo();

    public RepoModelo() {
        super(ModeloHeladera.class);
    }
}
