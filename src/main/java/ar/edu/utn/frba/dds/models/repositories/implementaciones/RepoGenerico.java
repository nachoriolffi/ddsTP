package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class RepoGenerico<T> implements WithSimplePersistenceUnit {

    protected final EntityTransaction transaction = entityManager().getTransaction();

    private final Class<T> entityClass;

    protected RepoGenerico(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void agregar(T entidad) {
        persist(entidad);
        transaction.commit();
    }

    public void modificar(T entidad) {
        merge(entidad);
        transaction.commit();
    }

    public void eliminar(T entidad) {
        remove(entidad);
        transaction.commit();
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
