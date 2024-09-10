package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;

public class RepoDonacionDinero extends RepoGenerico<DonacionDinero>{

    public static RepoDonacionDinero INSTANCE = new RepoDonacionDinero();

    public RepoDonacionDinero() {
        super(DonacionDinero.class);
    }
}
