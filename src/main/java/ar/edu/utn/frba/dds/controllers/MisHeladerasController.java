package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class MisHeladerasController extends BaseController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        model.put("title", "Mis Heladeras");

        context.render("donaciones/misHeladeras.hbs", model);
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
