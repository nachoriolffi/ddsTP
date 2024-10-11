package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.MotivoDistribucion;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDistribucionVianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.sql.Date;
import java.text.ParseException;

public class DistribuirViandasController implements ICrudViewsHandler {
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

        //Esto es para la persona humana, así que hay que chequear que tenga ese rol
        //también se podría asumir que cada heladera tiene un nombre unico, entonces la recuperamos por el nombre
        //en este caso solo se registra la contribución, no se hace la disribución

        //fechaDistribucion -> es la fecha en la que se van a distribuir las viandas
        //fechaColaboracion -> es la fecha en la que colaborador completo el formulario

        Long heladeraOrigen_id = Long.valueOf(context.formParam("heladera-origen"));
        Long heladeraDestino_id = Long.valueOf(context.formParam("heladera-destino"));

        Heladera heladeraOrigen =  RepoHeladeras.INSTANCE.buscar(heladeraOrigen_id);
        Heladera heladeraDestino =  RepoHeladeras.INSTANCE.buscar(heladeraDestino_id);

        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setHeladeraOrigen(heladeraOrigen);
        distribucionVianda.setHeladeraDestino(heladeraDestino);

        MotivoDistribucion motivo = MotivoDistribucion.valueOf(context.formParam("motivo"));// revisar

        distribucionVianda.setMotivo(motivo);

        distribucionVianda.setCantidadViandas(Integer.valueOf(context.formParam("cantidad-viandas")));

        distribucionVianda.setFechaDistribucion(Date.valueOf(context.formParam("fecha-distribucion")));//probar
        distribucionVianda.setFechaColaboracion(new java.util.Date());

        RepoDistribucionVianda.INSTANCE.agregar(distribucionVianda);

        //Lo siguiente se haría cuando el colaborador asista personalmente a realizar la distribución:
        //Habría que quitar las viandas de la heladera A segun la cantidad y agregarlas a la heladera B
        //Despues hay que hacer un update o modificar
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
