package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroIncidente;


public class RepoRegistrosVisita extends RepoGenerico<RegistroIncidente> {

    public static RepoRegistrosVisita INSTANCE = new RepoRegistrosVisita();


    public RepoRegistrosVisita () {
        super(RegistroIncidente.class);
    }
}
