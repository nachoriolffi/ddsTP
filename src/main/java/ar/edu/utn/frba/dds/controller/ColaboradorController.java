package ar.edu.utn.frba.dds.controller;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.factories.FactoryColaborador;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoColaborador;

public class ColaboradorController {

    // inyeccion de dependencias
    private final IRepoColaborador colaboradorRepository;

    public ColaboradorController(IRepoColaborador colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public void crearColaborador(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = FactoryColaborador.crearColaborador(colaboradorDTO);
        this.colaboradorRepository.agregarColaborador(colaborador);
    }
}
