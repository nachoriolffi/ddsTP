package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import java.util.List;


public class RepoViandas extends RepoGenerico<Vianda> {

    public static RepoViandas INSTANCE = new RepoViandas();



    public RepoViandas() {
        super(Vianda.class);
    }

    public List<Vianda> buscarViandasPorHeladeraId(Long heladeraId) {
        String query = "SELECT v FROM Vianda v WHERE v.heladera.id = :heladeraId";
        return entityManager.createQuery(query, Vianda.class)
                .setParameter("heladeraId", heladeraId)
                .getResultList();
    }
}
