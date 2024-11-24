package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.OfertaCanje;

public class RepoOfertaCanjeada extends RepoGenerico<OfertaCanje> {
    public static RepoOfertaCanjeada INSTANCE = new RepoOfertaCanjeada();

    public RepoOfertaCanjeada() {
        super(OfertaCanje.class);
    }
}
