package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReporte;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.nio.file.Paths;
import java.util.*;

public class ReporteController implements ICrudViewsHandler {

    private RepoReporte repoReporte = RepoReporte.INSTANCE;

    @Override
    public void index(Context context) {
        List<Reporte> reportes = this.repoReporte.buscarTodos();
        Map<String, Object> model = new HashMap<>();
        model.put("pdfs", reportes);
        model.put("title", "Reportes PDFs");
        context.render("visualizarReportes.hbs", model);
    }

    @Override
    public void show(Context context) {
        Map<String, Object> model = new HashMap<>();

        Long idReporte = Long.valueOf(context.pathParam("id")); // Obtener el ID del reporte desde la URL
        List<Reporte> reportes = this.repoReporte.buscarTodos();
        Optional<Reporte> reporte = Optional.ofNullable(this.repoReporte.buscar(idReporte));

        if (reporte.isPresent()) {
            String fileName = Paths.get(reporte.get().getPathDocumento()).getFileName().toString();
            String pdfUrl = "/pdfs/" + fileName; // Asegúrate que la URL del PDF sea accesible

            // Pasa la lista de reportes y el reporte seleccionado al modelo
            model.put("pdfs", reportes);
            model.put("reporte", reporte.get()); // Asegúrate de pasar el reporte correctamente
            model.put("pathDocumento", pdfUrl);
            model.put("title", "Reportes PDFs");

            // Renderizar la vista con el modelo completo
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
