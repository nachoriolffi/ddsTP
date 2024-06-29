package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.HeladeraInputDTO;
import ar.edu.utn.frba.dds.services.IHeladeraService;

public class HeladeraController {
    private IHeladeraService heladeraServcie;

    public HeladeraController(IHeladeraService heladeraServcie) {
        this.heladeraServcie = heladeraServcie;
    }

    public Object crear(Object solicitud){
        HeladeraInputDTO dto = (HeladeraInputDTO) solicitud;

       return this.heladeraServcie.crear(dto);

    }
}
