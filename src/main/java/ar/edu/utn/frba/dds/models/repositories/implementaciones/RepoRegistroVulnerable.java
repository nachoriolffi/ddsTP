package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RegistroVulnerable;

public class RepoRegistroVulnerable extends RepoGenerico<RegistroVulnerable>{

    public static RepoRegistroVulnerable INSTANCE = new RepoRegistroVulnerable();

    public RepoRegistroVulnerable (){
        super(RegistroVulnerable.class);
    }
}
