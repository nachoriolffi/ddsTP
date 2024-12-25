package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Local;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Localidad;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Provincia;

public class RepoLocalidad extends RepoGenerico<Localidad> {

    public static RepoLocalidad INSTANCE = new RepoLocalidad();


    public RepoLocalidad() {
        super(Localidad.class);
    }


}
