package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
