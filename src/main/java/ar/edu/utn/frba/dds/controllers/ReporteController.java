package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReporte;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.*;

public class ReporteController implements ICrudViewsHandler {

    private RepoReporte repoReporte = RepoReporte.INSTANCE;

    @Override
    public void index(Context context) {
        List<Reporte> reportes = this.repoReporte.buscarTodos();
        Map<String, Object> model = new HashMap<>();
        model.put("pdfs", reportes);
        model.put("title", "reportes");
        context.render("visualizarReportes.hbs", model);
    }

    @Override
    public void show(Context context) {
        List<Reporte> reportes = this.repoReporte.buscarTodos();
        Map<String, Object> model = new HashMap<>();
        model.put("pdfs", reportes);
        model.put("title", "reportes");
        Long idReporte = Long.valueOf(context.pathParam("id"));
        Optional<Reporte> reporte = Optional.ofNullable(this.repoReporte.buscar(idReporte));

        if (reporte.isPresent()) {
            model.put("reporte", reporte.get());
            model.put("pathDocumento", reporte.get().getPathDocumento()); // Asegúrate de que este método exista
            context.render("visualizarReportes.hbs", model);
        } else {

            context.status(HttpStatus.NOT_FOUND);
            context.redirect("/error404");
        }
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
