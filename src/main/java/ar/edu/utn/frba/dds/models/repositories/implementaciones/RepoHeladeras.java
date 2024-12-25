package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.suscripcion.ObserverColaborador;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Heladera, List<ObserverColaborador>> findHeladerasAndSuscripcionesBySuscriptorId(Long suscriptorId) {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT h, o FROM Heladera h JOIN h.observers o WHERE o.suscriptor.id = :suscriptorId", Object[].class);
        query.setParameter("suscriptorId", suscriptorId);
        List<Object[]> results = query.getResultList();

        Map<Heladera, List<ObserverColaborador>> heladerasAndSuscripciones = new HashMap<>();
        for (Object[] result : results) {
            Heladera heladera = (Heladera) result[0];
            ObserverColaborador observerColaborador = (ObserverColaborador) result[1];
            heladerasAndSuscripciones
                    .computeIfAbsent(heladera, k -> new ArrayList<>())
                    .add(observerColaborador);
        }
        return heladerasAndSuscripciones;
    }

    public List<Heladera> buscarHeladeras() {

        return RepoHeladeras.INSTANCE.buscarTodos().stream().
                filter(heladera -> heladera.getEstaActiva()
                        && !heladera.getDadaDeBaja()).collect(Collectors.toList());
    }

}
