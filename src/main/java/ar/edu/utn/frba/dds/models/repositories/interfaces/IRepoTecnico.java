package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;

import java.util.Optional;

public interface IRepoTecnico {
    void agregarTecnico(Tecnico tecnico);
    void eliminarTecnico(Tecnico tecnico);
    Optional<Tecnico> buscarTecnico(Long id);
}
