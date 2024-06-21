package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;

public interface IRepoColaborador {

     void agregarColaborador(Colaborador colaborador);
     void eliminarColaborador(Colaborador colaborador);
     void borrarColaboradores();

}
