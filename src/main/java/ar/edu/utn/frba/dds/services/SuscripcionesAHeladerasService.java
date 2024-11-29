package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.outputs.DistribucionViandaOutputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.SuscripcionesAHeladerasOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;


import java.util.List;

public class SuscripcionesAHeladerasService {
    public List<Heladera> buscarHeladeras(Usuario usuario) {
        return RepoHeladeras.INSTANCE.buscarTodos();
    }

    public Colaborador buscarColaborador(Usuario usuario) {
        return RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
    }
    /*
    public List<SuscripcionesAHeladerasOutputDTO> buscarSuscripcionesAHeladeras(Usuario usuario) {

        Colaborador colaborador = this.buscarColaborador(usuario);



    }*/

}