package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;


import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.Georef;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.GeorefService;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.GeorefInitializer;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCoordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoModelo;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoViandas;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeladeraController extends BaseController implements ICrudViewsHandler {


    RepoHeladeras repositorioHeladeras = RepoHeladeras.INSTANCE;


    @Override
    public void index(Context context) {

        List<Heladera> heladeras = RepoHeladeras.INSTANCE.buscarTodos();
        List<ModeloHeladera> modelos = RepoModelo.INSTANCE.buscarTodos();

        Map<String,Object> model = new HashMap<>();
        model.put("title","Heladeras");
        model.put("heladeras", heladeras);
        model.put("modelos", modelos);


        context.render("heladeras/heladeras.hbs",model);

    }

    @Override
    public void show(Context context) {
        String heladeraId = context.pathParam("heladeraId");
        Heladera heladera = repositorioHeladeras.buscar(Long.parseLong(heladeraId));

        if (heladera != null) {
            Map<String, Object> model = new HashMap<>();
            model.put("nombre", heladera.getNombre());
            model.put("direccion", "avenida medrano 951");
            model.put("latitud", heladera.getCoordenada().getLatitud());
            model.put("longitud", heladera.getCoordenada().getLongitud());
            model.put("viandas", RepoViandas.INSTANCE.buscarViandasPorHeladeraId(heladera.getId()));
            model.put("aperturas", heladera.getAperturas());
            // model.put("alertas", heladera.getAlertas());

            context.render("heladeras/verHeladera.hbs", model);
        } else {
            context.status(404).result("Heladera no encontrada");
        }
    }
    @Override
   public void create(Context context) {
        String nombre = context.formParam("nombre");
        String modelo = context.formParam("modelo");
        String calle = context.formParam("calle");
        String altura = context.formParam("altura");
        String piso = context.formParam("piso");


        ModeloHeladera modeloHeladera = RepoModelo.INSTANCE.buscar(Long.parseLong(modelo));
        System.out.println(modeloHeladera.getNombreModelo());
        Heladera heladera = new Heladera();
        heladera.setNombre(nombre);
        heladera.setModelo(modeloHeladera);
        Georef georef = GeorefInitializer.initializeGeoref();
        Coordenada coordenada = georef.obtenerCoordenadasPorDireccion(calle + altura);
        RepoCoordenada.INSTANCE.agregar(coordenada);
        heladera.setCoordenada(coordenada);
        heladera.setFechaPuestaFunc(new java.util.Date());
        heladera.setEstaActiva(true);

        // Guardar la nueva Heladera en el repositorio
        repositorioHeladeras.agregar(heladera);

        // Redirigir a la lista de heladeras
        context.redirect("/heladeras");
    }
    @Override
    public void save(Context context){

    }
    @Override
    public void edit(Context context){

    }
    @Override
    public void update(Context context){

    }
    @Override
    public void delete(Context context){

    }

}

