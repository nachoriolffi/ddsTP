package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class RegistroVulnerableController extends BaseController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarHumano(context, model);
        if (usuario != null) {
            model.put("title", "Registrar Persona Vulnerable");
            context.render("logs/registroVulnerable.hbs", model);
        } else {
            context.status(403);
        }

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
