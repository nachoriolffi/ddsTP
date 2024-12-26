package ar.edu.utn.frba.dds.congif;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class ServiceLocator {

    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if (instance == null) instance = new ServiceLocator();
        return instance;
    }

    public static <T> T instanceOf(Class<T> c) {
        try {
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
