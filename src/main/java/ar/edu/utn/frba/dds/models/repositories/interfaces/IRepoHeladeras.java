package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;

import java.util.List;

public interface IRepoHeladeras {
    void agregarHeladera(Heladera heladera);
    void eliminarHeladera(Heladera heladera);
    List<Heladera> traerHeladeras();
    Heladera buscarHeladera(Heladera heladera);
}
