package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;

public class RepoDistribucionVianda extends RepoGenerico<DistribucionVianda> {

    public static RepoDistribucionVianda INSTANCE = new RepoDistribucionVianda();

    public RepoDistribucionVianda() {
        super(DistribucionVianda.class);
    }
}
