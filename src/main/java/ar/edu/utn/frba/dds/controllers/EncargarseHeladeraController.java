package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCoordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoModelo;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.SimpleDateFormat;
import java.util.*;

public class EncargarseHeladeraController  extends BaseController implements ICrudViewsHandler {

    private RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;

    @Override
    public void index(Context context) {
        List<Heladera> heladeras = this.repoHeladeras.buscarTodos();
        List<HeladeraOutputDTO> heladeraOutputDTOS = new ArrayList<>();
        for (Heladera heladera : heladeras) {
            HeladeraOutputDTO heladeraOutputDTO = new HeladeraOutputDTO();
            heladeraOutputDTO.setNombre(heladera.getNombre());
            heladeraOutputDTO.setCapacidad(heladera.getModelo().getCantidadMaximaDeViandas());
            heladeraOutputDTO.setDireccion(String.valueOf(heladera.getCoordenada().getLatitud()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            heladeraOutputDTO.setFechaPuestaFunc(sdf.format(heladera.getFechaPuestaFunc()));
            heladeraOutputDTOS.add(heladeraOutputDTO);
        }
        Map<String, Object> model = new HashMap<>();
        List<ModeloHeladera> modelos = RepoModelo.INSTANCE.buscarTodos();
        model.put("modelos", modelos);
        model.put("title", "Encargarse De Heladera");
        model.put("heladeras", heladeraOutputDTOS);
        context.render("donaciones/encargarseDeHeladera.hbs", model);
    }

    @Override
    public void show(Context context) {
        // TODO CUANDO HAYA TIEMPO, no es tan importante por ahora
    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        System.out.println("VOY A GUARDAR UNA HELADERA");
        Heladera heladera = new Heladera();
        ModeloHeladera modeloHeladera = RepoModelo.INSTANCE.buscar(Long.valueOf(Objects.requireNonNull(context.formParam("modeloHeladera"))));
        System.out.println("MODELO " + modeloHeladera.getNombreModelo());
        RepoCoordenada repoCoordenada = RepoCoordenada.INSTANCE;
        Coordenada coordenada = new Coordenada(-34.598630, -58.419962);
        repoCoordenada.agregar(coordenada);
        heladera.setCoordenada(coordenada);
        heladera.setModelo(modeloHeladera);
        heladera.setEstaActiva(true);
        heladera.setViandasDisponibles(modeloHeladera.getCantidadMaximaDeViandas());
        heladera.setNombre(context.formParam("nombreHeladera"));
        heladera.setFechaPuestaFunc(new Date());
        Direccion direccion = new Direccion();
        direccion.setAltura(Integer.valueOf(Objects.requireNonNull(context.formParam("altura"))));
        direccion.setCalle(new Calle(context.formParam("calle")));
        direccion.setPiso(Integer.valueOf(Objects.requireNonNull(context.formParam("piso"))));
        heladera.setDireccion(direccion);
        heladera.setReceptorTemperatura(new ReceptorTemperatura());
        heladera.setReceptorMovimiento(new ReceptorMovimiento());
        this.repoHeladeras.agregar(heladera);
        context.redirect("/encargarseHeladera");
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
