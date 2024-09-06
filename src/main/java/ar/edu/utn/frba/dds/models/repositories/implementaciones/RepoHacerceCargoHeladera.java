package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.HacerseCargoDeHeladera;

public class RepoHacerceCargoHeladera extends RepoGenerico<HacerseCargoDeHeladera>{

    public static  RepoHacerceCargoHeladera INSTANCE = new RepoHacerceCargoHeladera();

    public RepoHacerceCargoHeladera (){
        super(HacerseCargoDeHeladera.class);
    }

}
