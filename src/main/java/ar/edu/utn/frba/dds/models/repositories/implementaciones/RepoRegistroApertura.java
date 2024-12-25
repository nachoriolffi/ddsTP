package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.RegistroApertura;

public class RepoRegistroApertura extends RepoGenerico<RegistroApertura> {
    public static RepoRegistroApertura INSTANCE = new RepoRegistroApertura();
    public RepoRegistroApertura() {
        super(RegistroApertura.class);
    }
}

