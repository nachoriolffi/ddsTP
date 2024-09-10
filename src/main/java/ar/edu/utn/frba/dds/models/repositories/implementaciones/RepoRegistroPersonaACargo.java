package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.vulnerable.RegistroMenorACargo;

public class RepoRegistroPersonaACargo extends RepoGenerico<RegistroMenorACargo> {

    public static RepoRegistroPersonaACargo INSTANCE = new RepoRegistroPersonaACargo();

    public RepoRegistroPersonaACargo() {
        super(RegistroMenorACargo.class);
    }
}
