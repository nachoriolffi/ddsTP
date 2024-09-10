package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionVianda;

public class RepoDonacionVianda extends RepoGenerico<DonacionVianda>{

    public static  RepoDonacionVianda INSTANCE = new RepoDonacionVianda();

    public RepoDonacionVianda (){
        super(DonacionVianda.class);
    }
}
