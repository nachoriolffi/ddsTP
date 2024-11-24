package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.services.CargaMasivaService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CargaMasivaController extends BaseController implements ICrudViewsHandler {

    CargaMasivaService cargaMasivaService = new CargaMasivaService();

    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarAdmin(context, model);
        if (usuario != null) {
            model.put("title", "Carga Masiva CSV");
            context.render("cargaMasiva/cargaMasiva.hbs", model);
        } else {
            context.status(403);
        }
    }

    public void cargaMasiva(Context context) {

        UploadedFile file = context.uploadedFile("csv-file");
        if (file != null) {
            CompletableFuture.runAsync(() -> cargaMasivaService.realizarLecturaCSV(file));
            context.redirect("/cargaMasiva");
        } else
            context.redirect("/cargaMasiva");
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
