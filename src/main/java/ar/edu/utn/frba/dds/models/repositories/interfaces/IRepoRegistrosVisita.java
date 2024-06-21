package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroVisita;

public interface IRepoRegistrosVisita {
     void agregarRegistroVisita(RegistroVisita registro);
     void eliminarRegistroVisita(RegistroVisita registro);
     void borrarRegistrosVisitas();
}
