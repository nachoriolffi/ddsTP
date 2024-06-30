package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;



public interface IRepoTarjeta {
    void agregarTarjeta(Tarjeta tarjeta);
    void eliminarTarjeta(Tarjeta tarjeta);
    Tarjeta buscarTarjeta(Integer id);
}
