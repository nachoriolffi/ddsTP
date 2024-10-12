package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

public enum MotivoDistribucion {
    FALTA_VIANDAS_H_DESTINO("Faltan Viandas en Heladera Destino"),
    DESPERFECTO_H_ORIGEN("Desperfecto en Heladera Origen");

    private final String descripcion;

    MotivoDistribucion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {// pongo esto para que lo castee a String de esa forma y se vea así en la vista (no afecta a como se persiste en la bd)
        return descripcion;
    }
}
