package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RepoRubroColaborador extends RepoGenerico<RubroColaborador>{

    public static RepoRubroColaborador INSTANCE = new RepoRubroColaborador();

    public RepoRubroColaborador() {
        super(RubroColaborador.class);
    }

    public RubroColaborador buscarPorNombre(String nombre) {
        TypedQuery<RubroColaborador> query = entityManager.createQuery("SELECT r FROM RubroColaborador r WHERE r.nombre = :nombre", RubroColaborador.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }
}

