package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import io.javalin.http.Context;

import javax.ws.rs.ext.ContextResolver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfertaController extends BaseController {

    RepoOferta repositorioOferta = RepoOferta.INSTANCE;


    @Override
    public void index(Context ctx) {

        List<Oferta> ofertas = repositorioOferta.buscarTodos();

        Map<String, Object> model = new HashMap<>();

        model.put("ofertas", ofertas);

        ctx.render("templates/ofertas.hbs", model);
    }
}
