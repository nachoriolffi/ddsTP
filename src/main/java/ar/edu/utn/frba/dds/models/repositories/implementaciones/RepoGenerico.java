package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class
RepoGenerico<T> implements WithSimplePersistenceUnit {
    private final Class<T> entityClass;
    public static EntityManager entityManager;

    static {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        entityManager = emf.createEntityManager();
    }

    protected RepoGenerico(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void agregar(T entidad) {
        beginTransaction();
        persist(entidad);
        commitTransaction();
    }

    public void modificar(T entidad) {
        beginTransaction();
        merge(entidad);
        commitTransaction();
    }

    public void eliminar(T entidad) {
        beginTransaction();
        remove(entidad);
        commitTransaction();
    }

    public T buscar(Long id) {
        return find(entityClass, id);
    }

    public List<T> buscarTodos() {
        return entityManager()
                .createQuery("select e from " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();


    }


}