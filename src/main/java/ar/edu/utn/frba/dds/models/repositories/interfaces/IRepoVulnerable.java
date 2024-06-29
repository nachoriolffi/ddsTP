package ar.edu.utn.frba.dds.models.repositories.interfaces;


import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;

import java.util.Optional;

public interface IRepoVulnerable {
    void agregarVulnerable(Vulnerable vulnerable );
    void eliminarVulnerable(Vulnerable vulnerable);
    Optional<Vulnerable> buscarVulnerable(Long id);
}
