package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;

public class RepoVulnerable extends RepoGenerico<Vulnerable>{

    public static RepoVulnerable INSTANCE = new RepoVulnerable();

    public RepoVulnerable() {
        super(Vulnerable.class);
    }

}
