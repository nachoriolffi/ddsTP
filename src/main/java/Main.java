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