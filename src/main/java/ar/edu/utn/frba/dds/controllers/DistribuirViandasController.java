package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.MotivoDistribucion;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.sql.Date;
import java.text.ParseException;

public class DistribuirViandasController  extends BaseController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {
        context.render("donaciones/distribuirViandas.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws ParseException {
        System.out.println("heladera-origen: " + context.formParam("heladera-origen"));
        System.out.println("heladera-destino: " + context.formParam("heladera-destino"));
        System.out.println("motivo: " + context.formParam("motivo"));
        System.out.println("cantidad-viandas: " + context.formParam("cantidad-viandas"));
        System.out.println("fecha-distribucion: " + context.formParam("fecha-distribucion"));

        Heladera heladeraOrigen = new Heladera();//lo estoy harcodeando, pero deberia ser un getHeladeraByNombre
        Heladera heladeraDestino = new Heladera();
        //context.formParam("heladera-origen");
        //context.formParam("heladera-destino");


        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setHeladeraOrigen(heladeraOrigen);
        distribucionVianda.setHeladeraDestino(heladeraDestino);

        MotivoDistribucion motivo = MotivoDistribucion.valueOf(context.formParam("motivo"));// revisar

        distribucionVianda.setMotivo(motivo);

        distribucionVianda.setCantidadViandas(Integer.valueOf(context.formParam("cantidad-viandas")));

        distribucionVianda.setFechaDistribucion(Date.valueOf(context.formParam("fecha-distribucion")));//probar
        distribucionVianda.setFechaColaboracion(new java.util.Date());

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
