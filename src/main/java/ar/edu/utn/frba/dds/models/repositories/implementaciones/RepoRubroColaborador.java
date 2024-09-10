package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;

public class RepoRubroColaborador extends RepoGenerico<RubroColaborador>{

    public static RepoRubroColaborador INSTANCE = new RepoRubroColaborador();

    public RepoRubroColaborador() {
        super(RubroColaborador.class);
    }
}

