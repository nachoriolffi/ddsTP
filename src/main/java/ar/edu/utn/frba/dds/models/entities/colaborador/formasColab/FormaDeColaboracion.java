package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;

public interface FormaDeColaboracion {

    public double sumarPuntosA(Colaborador colaborador);
    public TipoColaboracion getTipoColaboracion();
    public Integer getCantidadViandas();
}
