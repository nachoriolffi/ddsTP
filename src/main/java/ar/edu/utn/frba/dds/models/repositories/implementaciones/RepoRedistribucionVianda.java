package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RegistroVulnerable;

public class RepoRedistribucionVianda extends RepoGenerico<DistribucionVianda> {

    public static RepoRedistribucionVianda INSTANCE = new RepoRedistribucionVianda();

    public RepoRedistribucionVianda() {
        super(DistribucionVianda.class);
    }
}
