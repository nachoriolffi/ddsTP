package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.dtos.VulnerableDTO;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.services.interfaces.IVulnerableService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VulnerableController implements  ICrudViewsHandler {

    private final IVulnerableService vulnerableService;

    public VulnerableController(IVulnerableService vulnerableService) {
        this.vulnerableService = vulnerableService;
    }

    public Object crear(Object solicitud) throws ParseException {
        VulnerableDTO vulnerableDTO = (VulnerableDTO) solicitud;
        return this.vulnerableService.crear(vulnerableDTO);
    }

    @Override
    public void index(Context ctx) {
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

}
