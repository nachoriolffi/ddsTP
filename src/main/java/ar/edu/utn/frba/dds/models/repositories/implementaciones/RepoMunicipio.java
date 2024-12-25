package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Municipio;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Provincia;

public class RepoMunicipio  extends RepoGenerico<Municipio> {

    public static RepoMunicipio INSTANCE = new RepoMunicipio();


    public RepoMunicipio() {
        super(Municipio.class);
    }


}
