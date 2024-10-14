package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReporte;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.nio.file.Paths;
import java.util.*;

public class ReporteController extends BaseController implements ICrudViewsHandler {

    private RepoReporte repoReporte = RepoReporte.INSTANCE;

    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarAdmin(context, model);
        if (usuario != null) {
            List<Reporte> reportes = this.repoReporte.buscarTodos();
            model.put("pdfs", reportes);
            model.put("title", "Reportes PDFs");
            context.render("visualizarReportes.hbs", model);
        } else {
            context.status(403);
        }

    }

    @Override
    public void show(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarAdmin(context, model);
        if (usuario != null) {
            
            List<Reporte> reportes = this.repoReporte.buscarTodos();
            Reporte reporte = this.repoReporte.buscar(Long.valueOf(context.pathParam("id")));
            if (reporte != null) {

                String fileName = Paths.get(reporte.getPathDocumento()).getFileName().toString();
                String pdfUrl = "/pdfs/" + fileName;

                model.put("pdfs", reportes);
                model.put("reporte", reporte);
                model.put("pathDocumento", pdfUrl);
                model.put("title", "Reportes PDFs");
                context.render("visualizarReportes.hbs", model);

            } else {
                context.status(404);
            }
        } else {
            context.status(403);
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
