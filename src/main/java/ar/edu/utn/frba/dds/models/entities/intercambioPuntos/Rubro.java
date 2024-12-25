package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

public enum Rubro {
    SALUD("Salud"),
    EDUCACION("Educacion"),
    GASTRONOMIA("Gastronomia"),
    HOGAR("Hogar"),
    ELECTRONICA("Electronica"),
    BIENESTAR("Bienestar"),
    INTERIOR("Interior"),
    TECNOLOGIA("Tecnologia");

    private final String descripcion;

    Rubro(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    // Método para obtener el Rubro basado en la descripción
    public static Rubro fromDescripcion(String descripcion) {
        for (Rubro rubro : Rubro.values()) {
            if (rubro.getDescripcion().equalsIgnoreCase(descripcion)) {
                return rubro; // Devuelve el Rubro que coincide
            }
        }
        return null; // Devuelve null si no hay coincidencias
    }

}
