package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.lectorCSV.LectorColaborador;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;

public class CargaMasivaController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {
        context.render("cargaMasiva/cargaMasiva.hbs");
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

    public void cargaMasiva(Context context) {
        UploadedFile file = context.uploadedFile("csv-file");

        if (file == null) {
            context.redirect("/cargaMasiva");
            return;
        }
        FileUtil.streamToFile(file.content(), "src/main/resources/uploads/" + file.filename());
        LectorColaborador lector = new LectorColaborador();
        lector.leerCSV("src/main/resources/uploads/" + file.filename());
        context.redirect("/cargaMasiva");



    }
}
