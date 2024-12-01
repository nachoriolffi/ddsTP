package ar.edu.utn.frba.dds.models.entities.heladera.suscripcion;

public enum TipoSuscripcion {
    MUCHAS_VIANDAS("Queda lugar para almacenar n viandas"),
    VIANDAS_DISPONIBLES("Hay n viandas disponibles"),
    DESPERFECTO("Desperfecto");

    private final String descripcion;
    TipoSuscripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {// pongo esto para que lo castee a String de esa forma y se vea así en la vista (no afecta a como se persiste en la bd)
        return descripcion;
    }
}
