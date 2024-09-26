package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class EncargarseHeladeraController implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "encargarseHeladera");
        context.render("donaciones/encargarseDeHeladera.hbs", model);
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
