package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;
import ar.edu.utn.frba.dds.services.interfaces.IHeladeraService;

import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

public class HeladeraController implements ICrudViewsHandler {

    private IHeladeraService heladeraServcie;

    public HeladeraController(IHeladeraService heladeraServcie) {
        this.heladeraServcie = heladeraServcie;
    }

    public Object crear(Object solicitud) {
        HeladeraInputDTO dto = (HeladeraInputDTO) solicitud;
        return this.heladeraServcie.crear(dto);
    }


    public void index(Context context) {

    }
    @Override
    public void show(Context context){

    }
    @Override
    public void create(Context context){

    }
    @Override
    public void save(Context context){

    }
    @Override
    public void edit(Context context){

    }
    @Override
    public void update(Context context){

    }
    @Override
    public void delete(Context context){

    }

}

