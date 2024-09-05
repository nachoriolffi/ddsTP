package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Provincia;

public class RepoProvincia extends RepoGenerico<Provincia> {

    public static RepoProvincia INSTANCE = new RepoProvincia();


    public RepoProvincia() {
        super(Provincia.class);
    }


}
