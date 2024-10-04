package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;


import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoModelo;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeladeraController implements ICrudViewsHandler {



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
    public void show(Context context){

    }
    @Override
    public void create(Context context) {
        String nombre = context.formParam("nombre");
        String modelo = context.formParam("modelo");
        String calle = context.formParam("calle");
        String altura = context.formParam("altura");
        String piso = context.formParam("piso");

        // Buscar el piso en el repositorio



        // Crear una nueva instancia de Heladera
        Heladera heladera = new Heladera();
        heladera.setNombre(nombre);
        heladera.setModelo(RepoModelo.INSTANCE.buscar(Long.parseLong(modelo)));
       // heladera.setCalle(calle);
       // heladera.setAltura(altura);
        //heladera.setPiso(piso);

        // Guardar la nueva Heladera en el repositorio
        RepoHeladeras.INSTANCE.agregar(heladera);

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

