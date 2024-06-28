package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;

public interface IRepoVulnerable {

     void agregarVulnerable(Vulnerable vulnerable);

     void eliminarVulnerable(Vulnerable vulnerable);

     void borrarVulnerables();

}
