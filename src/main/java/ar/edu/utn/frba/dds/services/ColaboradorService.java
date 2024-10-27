package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorService {

    private final RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
    private final RepoUsuario repoUsuario = RepoUsuario.INSTANCE;

    public List<ColaboradorDTO> obtenerColaboradores() {
        List<Colaborador> colaboradores = repoColaborador.buscarTodos();
        List<ColaboradorDTO> colaboradorDTOS = new ArrayList<>();
        for (Colaborador c : colaboradores) {
            if (!c.getUsuario().getCuentaEliminada()) {
                ColaboradorDTO cDTO = new ColaboradorDTO();
                cDTO.setID(String.valueOf(c.getId()));
                cDTO.setNombreYApellido(c.getNombre() + " " + c.getApellido());
                cDTO.setTipoColaborador(String.valueOf(c.getTipoPersona()));
                cDTO.setFechaDeNacimiento(String.valueOf(c.getFechaDeNacimiento()));
                cDTO.setRazonSocial(c.getRazonSocial());
                cDTO.setJurisdiccion(String.valueOf(c.getTipoJuridiccion()));
                colaboradorDTOS.add(cDTO);
            }
        }
        return colaboradorDTOS;
    }

    public void darDeBaja(Long idColaborador) {
        Colaborador colaborador = repoColaborador.buscar(idColaborador);
        Usuario usuario = colaborador.getUsuario();
        usuario.setCuentaEliminada(true);
        repoUsuario.modificar(usuario);
    }
}
