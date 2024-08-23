package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.metamodel.EntityType;
import java.util.Set;

public class ContextTest implements SimplePersistenceTest {
        private Heladera heladera1;
        @Test
        void contextUp() {
            Assertions.assertNotNull(entityManager());
        }

        @Test
        void contextUpWithTransaction() throws Exception {
            heladera1 = new Heladera();
            withTransaction(() -> {
            });
        }

        @Test
        void testPersistAllEntities() {
            EntityManager em = entityManager();
            EntityTransaction tx = em.getTransaction();

            try {
                // Comienza una nueva transacción
                if (!tx.isActive()) {
                    tx.begin();
                }

                // Obtener el conjunto de entidades desde el EntityManager
                Set<EntityType<?>> entities = em.getMetamodel().getEntities();

                // Crear y persistir instancias para cada entidad
                for (EntityType<?> entityType : entities) {
                    Class<?> entityClass = entityType.getJavaType();

                    // Crear una instancia de la entidad
                    Object entityInstance = createInstance(entityClass);

                    // Persistir la entidad
                    em.persist(entityInstance);
                }

                // Confirma la transacción si todo fue bien
                if (tx.isActive()) {
                    tx.commit();
                }
            } catch (Exception e) {
                // Si ocurre una excepción, revierte la transacción
                if (tx.isActive()) {
                    tx.rollback();
                }
                e.printStackTrace();
                throw new RuntimeException("Error during entity persistence", e);
            } finally {
                em.close();
            }
        }

        // Método auxiliar para crear instancias de entidades
        private Object createInstance(Class<?> clazz) {
            try {
                return clazz.getConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error creating instance of " + clazz.getName(), e);
            }
        }
}
