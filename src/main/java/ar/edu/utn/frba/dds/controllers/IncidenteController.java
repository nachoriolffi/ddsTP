package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoGenerico;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import javax.ws.rs.PathParam;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IncidenteController extends BaseController  implements ICrudViewsHandler {
    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        verificarTecnico(context, model);
        Incidente incidente = RepoIncidente.INSTANCE.buscar(Long.parseLong(Objects.requireNonNull(context.pathParam("id"))));

        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
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
