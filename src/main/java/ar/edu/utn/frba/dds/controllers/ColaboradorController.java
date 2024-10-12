package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;

public class ColaboradorController extends BaseController implements ICrudViewsHandler {

    private RepoColaborador repositorioColaborador = RepoColaborador.INSTANCE;

    public ColaboradorController(RepoColaborador repositorioColaborador){
        this.repositorioColaborador = repositorioColaborador;
    }

    public void index(Context context){
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws ParseException {

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
