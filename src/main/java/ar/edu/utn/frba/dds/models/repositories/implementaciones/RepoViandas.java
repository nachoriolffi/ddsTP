package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoViandas;

import java.util.ArrayList;
import java.util.List;

public class RepoViandas extends RepoGenerico<Vianda> implements IRepoViandas {

    private static RepoViandas instancia = null;
    private List<Vianda> viandas;

    public static RepoViandas getInstancia() {
        if( instancia == null ) {
            instancia = new RepoViandas();
        }
        return instancia;
    }

    public RepoViandas() {
        super(Vianda.class);
        this.viandas = new ArrayList<>();
    }

    @Override
    public void agregarVianda(Vianda vianda) {
        this.viandas.add(vianda);
    }

    @Override
    public void eliminarVianda(Vianda vianda) {
        this.viandas.remove(vianda);
    }

    @Override
    public List<Vianda> listarViandas() {
        return this.viandas;
    }
}
