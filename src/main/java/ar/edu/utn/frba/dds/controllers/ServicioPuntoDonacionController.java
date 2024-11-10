package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.services.servicioDePuntos.AdapterPuntosDonacion;
import ar.edu.utn.frba.dds.services.servicioDePuntos.IAdapterPuntosDonacion;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ServicioPuntoDonacionController extends BaseController implements ICrudViewsHandler {

    IAdapterPuntosDonacion adapterPuntosDonacion = new AdapterPuntosDonacion();

    @Override
    public void index(Context context) {
        context.render("/servicio.hbs");
    }

    @Override
    public void show(Context context) {
        double latitude = Double.parseDouble(Objects.requireNonNull(context.formParam("latitude")));
        double longitude = Double.parseDouble(Objects.requireNonNull(context.formParam("longitude")));
        List<String> puntosDonacion;
        try {
            puntosDonacion = adapterPuntosDonacion.obtenerPuntosDonacion(latitude, longitude);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        context.json(puntosDonacion);
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
