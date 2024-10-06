package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Provincia;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoProvincia;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroHumanoController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {

        RepoProvincia repoProvincia = RepoProvincia.INSTANCE;
        List<Provincia> provincias = repoProvincia.buscarTodos();

        Map<String, Object> model = new HashMap<>();

        model.put("title", "Registro Humano");
        //model.put("provincias", provincias);

        context.render("logs/registroHumano.hbs", model);

        //context.render("logs/registroHumano.hbs");//estoy probando que la pagina cargue, todavía no está terminado
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
