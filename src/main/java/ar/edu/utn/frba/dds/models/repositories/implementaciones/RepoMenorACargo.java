package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.vulnerable.MenorACargo;

public class RepoMenorACargo extends RepoGenerico<MenorACargo> {

    public static RepoMenorACargo INSTANCE = new RepoMenorACargo();
    public RepoMenorACargo() {
        super(MenorACargo.class);
    }
}
