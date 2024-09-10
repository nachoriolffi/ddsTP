package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class RepoTecnico extends RepoGenerico<Tecnico> {

    public static RepoTecnico INSTANCE = new RepoTecnico();

    public RepoTecnico() {
        super(Tecnico.class);
    }

}
