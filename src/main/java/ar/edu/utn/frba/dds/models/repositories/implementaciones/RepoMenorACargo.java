package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.vulnerable.RegistroMenorACargo;

public class RepoMenorACargo extends RepoGenerico<RegistroMenorACargo> {

    public static RepoMenorACargo INSTANCE = new RepoMenorACargo();
    public RepoMenorACargo() {
        super(RegistroMenorACargo.class);
    }
}
