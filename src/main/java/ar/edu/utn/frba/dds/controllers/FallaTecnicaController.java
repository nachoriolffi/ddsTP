package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente.FALLA;

public class FallaTecnicaController extends BaseController implements ICrudViewsHandler {

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
        Heladera heladera = RepoHeladeras.INSTANCE.buscar(Long.valueOf(Objects.requireNonNull(context.formParam("id"))));
        nuevoIncidente.setHeladera(heladera);
        nuevoIncidente.setTipoIncidente(FALLA);
        nuevoIncidente.setDescripcion(context.formParam("descripcionFalla"));
        nuevoIncidente.setFecha(Date.valueOf(Objects.requireNonNull(context.formParam("diaYHora"))));
        nuevoIncidente.setPathFoto(context.formParam("fotoFalla"));

        this.repositorioIncidentes.agregar(nuevoIncidente);
        //O BIEN LANZO UNA PANTALLA DE EXITO
        //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
        context.redirect("/fallaTecnica");
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
