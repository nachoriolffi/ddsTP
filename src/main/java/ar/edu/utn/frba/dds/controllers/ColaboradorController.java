package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.services.interfaces.IColaboradorService;

public class ColaboradorController {

    private IColaboradorService colaboradorService;

    public ColaboradorController(IColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    public Object crear(Object solicitud) {
        ColaboradorDTO colaboradorDTO = (ColaboradorDTO) solicitud;
        return this.colaboradorService.crear(colaboradorDTO);
    }


}
