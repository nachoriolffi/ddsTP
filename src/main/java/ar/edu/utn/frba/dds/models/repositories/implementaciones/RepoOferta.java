package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;

public class RepoOferta extends RepoGenerico<Oferta> {
    public static RepoOferta INSTANCE = new RepoOferta();

    public RepoOferta() {
        super(Oferta.class);
    }
}
