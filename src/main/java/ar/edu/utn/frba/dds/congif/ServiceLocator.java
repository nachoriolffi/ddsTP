package ar.edu.utn.frba.dds.congif;

public class ServiceLocator {
    private static ServiceLocator instance = null;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public static <T> T instanceOf(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
