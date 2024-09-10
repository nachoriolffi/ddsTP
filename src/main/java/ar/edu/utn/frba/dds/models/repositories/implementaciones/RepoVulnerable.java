package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoVulnerable extends RepoGenerico<Vulnerable>{

    public static RepoVulnerable INSTANCE = new RepoVulnerable();

    public RepoVulnerable() {
        super(Vulnerable.class);
    }

}
