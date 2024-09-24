package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import javax.ws.rs.ext.ContextResolver;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfertaController implements ICrudViewsHandler {

    RepoOferta repositorioOferta = RepoOferta.INSTANCE;


    @Override
    public void index(Context ctx) {

            List<Oferta> ofertas = repositorioOferta.buscarTodos();

            Map<String, Object> model = new HashMap<>();
            model.put("title", "Ofertas");
            model.put("ofertas", ofertas);

            ctx.render("ofertas.hbs", model);

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
