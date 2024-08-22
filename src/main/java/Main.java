import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear EntityManagerFactory usando la configuración de persistence.xml
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("simple-persistence-unit");

            System.out.println("Tablas creadas exitosamente en la base de datos.");

            // Cerrar EntityManagerFactory
            entityManagerFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}