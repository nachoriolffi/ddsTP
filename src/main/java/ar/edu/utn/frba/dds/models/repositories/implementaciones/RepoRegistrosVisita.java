package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroVisita;


public class RepoRegistrosVisita extends RepoGenerico<RegistroVisita> {

    public static RepoRegistrosVisita INSTANCE = new RepoRegistrosVisita();


    public RepoRegistrosVisita () {
        super(RegistroVisita.class);
    }
}
