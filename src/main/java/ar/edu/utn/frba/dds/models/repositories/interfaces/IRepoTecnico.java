package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;

public interface IRepoTecnico {
    void agregarTecnico(Tecnico tecnico);
    void eliminarTecnico(Tecnico tecnico);
    Tecnico buscarTecnico(Tecnico tecnico);
}
