package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;

public class RepoCalle  extends RepoGenerico<Calle>{
    public static RepoCalle INSTANCE = new RepoCalle();
    public RepoCalle() {
        super(Calle.class);
    }
}
