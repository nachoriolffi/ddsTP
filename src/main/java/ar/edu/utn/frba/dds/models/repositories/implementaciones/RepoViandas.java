package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;


public class RepoViandas extends RepoGenerico<Vianda> {

    public static RepoViandas INSTANCE = new RepoViandas();

    public RepoViandas() {
        super(Vianda.class);
    }
}
