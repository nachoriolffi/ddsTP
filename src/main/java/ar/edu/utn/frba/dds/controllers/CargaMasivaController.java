package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.lectorCSV.LectorColaborador;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;

import java.util.HashMap;
import java.util.Map;

public class CargaMasivaController extends BaseController implements ICrudViewsHandler {

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
        if (file == null) {
            context.redirect("/cargaMasiva");
            // TODO AGREGAR QUE SE TIRE UN MENSAJE DE ERROR
        } else {
            String rutaUploads = "src/main/resources/uploads/";
            FileUtil.streamToFile(file.content(), rutaUploads + file.filename());
            LectorColaborador lector = new LectorColaborador();
            lector.leerCSV(rutaUploads + file.filename());
            context.redirect("/cargaMasiva");
            // TODO AGREGAR QUE SE TIRE UN MENSAJE DE EXITO
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
