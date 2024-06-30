package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;

import java.util.Optional;

public interface IRepoColaborador {

     void agregarColaborador(Colaborador colaborador);
     Colaborador buscar(Integer id);
     void eliminarColaborador(Colaborador colaborador);
     void borrarColaboradores();

}
