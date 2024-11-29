package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepoHeladeras extends RepoGenerico<Heladera> {

    public static RepoHeladeras INSTANCE = new RepoHeladeras();
    public RepoHeladeras () {
        super(Heladera.class);
    }

    public List<Heladera> findBySuscriptorId(Long suscriptorId) {
        TypedQuery<Heladera> query = entityManager.createQuery(
                "SELECT h FROM Heladera h JOIN h.observers o WHERE o.suscriptor.id = :suscriptorId", Heladera.class);
        query.setParameter("suscriptorId", suscriptorId);
        return query.getResultList();
    }

}
