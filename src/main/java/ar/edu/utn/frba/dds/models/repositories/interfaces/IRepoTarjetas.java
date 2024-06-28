package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;

import java.util.List;

public interface IRepoTarjetas {

    void agregarTarjeta(Tarjeta tarjeta);
    void eliminarTarjeta(Tarjeta tarjeta);
    List<Tarjeta> listarTarjetas();
}
