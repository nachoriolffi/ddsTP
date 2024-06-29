package ar.edu.utn.frba.dds.models.repositories.interfaces;


import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;

import java.util.List;

public interface IRepoIncidente {
    void agregarIncidente(Incidente incidente);
    void eliminarIncidente(Incidente incidente);
    List<Incidente> traerIncidentes();
    Incidente buscarIncidente(Incidente incidente);
}
