package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.dtos.VulnerableDTO;
import ar.edu.utn.frba.dds.services.interfaces.IVulnerableService;

import java.text.ParseException;

public class VulnerableController {

    private final IVulnerableService vulnerableService;

    public VulnerableController(IVulnerableService vulnerableService) {
        this.vulnerableService = vulnerableService;
    }

    public Object crear(Object solicitud) throws ParseException {
        VulnerableDTO vulnerableDTO = (VulnerableDTO) solicitud;
        return this.vulnerableService.crear(vulnerableDTO);
    }

}
