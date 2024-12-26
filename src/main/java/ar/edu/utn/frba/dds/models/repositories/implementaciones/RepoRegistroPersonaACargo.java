package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.vulnerable.MenorACargo;

public class RepoRegistroPersonaACargo extends RepoGenerico<MenorACargo> {

    public static RepoRegistroPersonaACargo INSTANCE = new RepoRegistroPersonaACargo();

    public RepoRegistroPersonaACargo() {
        super(MenorACargo.class);
    }
}
