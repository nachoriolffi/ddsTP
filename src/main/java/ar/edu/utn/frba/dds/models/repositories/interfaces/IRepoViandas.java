package ar.edu.utn.frba.dds.models.repositories.interfaces;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import java.util.List;

public interface IRepoViandas {

    void agregarVianda(Vianda tarjeta);
    void eliminarVianda(Vianda tarjeta);
    List<Vianda> listarViandas();
}
