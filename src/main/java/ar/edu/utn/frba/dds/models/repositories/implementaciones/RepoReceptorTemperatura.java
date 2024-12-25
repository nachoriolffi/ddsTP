package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;

public class RepoReceptorTemperatura extends RepoGenerico<ReceptorTemperatura> {
    public static RepoReceptorTemperatura INSTANCE = new RepoReceptorTemperatura();

    public RepoReceptorTemperatura() {
        super(ReceptorTemperatura.class);
    }
}
