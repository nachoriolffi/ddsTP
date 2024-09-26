package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente.FALLA;

public class IncidenteController implements ICrudViewsHandler {

    private RepoIncidente repositorioIncidentes = RepoIncidente.INSTANCE;
    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "fallaTecnica");
        context.render("incidentes/reportarFallaTecnica.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        Incidente nuevoIncidente = new Incidente();

        nuevoIncidente.setTipoIncidente(FALLA);
        nuevoIncidente.setDescripcion(context.formParam("descripcion"));
        //nuevoIncidente.setFecha(context.formParam("fecha"));
        nuevoIncidente.setPathFoto(context.formParam("pathFoto"));
        //nuevoIncidente.setTipoAlerta(NULL);;

        this.repositorioIncidentes.agregar(nuevoIncidente);
        //O BIEN LANZO UNA PANTALLA DE EXITO
        //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
        context.redirect("/reportarIncidente");
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
