package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.ModeloDTO;
import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoViandas;
import ar.edu.utn.frba.dds.services.HeladeraService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeladeraController extends BaseController implements ICrudViewsHandler {

    HeladeraService heladeraService = new HeladeraService();

    @Override
    public void index(Context context) {

        List<HeladeraOutputDTO> heladeras = heladeraService.obtenerHeladeras();
        List<ModeloHeladera> modelos = heladeraService.obtenerModelosHeladera();
        Map<String, Object> model = new HashMap<>();

        try {
            Usuario usuario = verificarSesion(context, model);
            if (usuario.getRol().equals(TipoRol.ADMIN)) {
                model.put("esAdmin", true);
            }
            model.put("title", "Heladeras");
            model.put("heladeras", heladeras);
            model.put("modelos", modelos);
            context.render("heladeras/heladeras.hbs", model);
        } catch (Exception e) {
            context.redirect("/iniciarSesion");
        }
    }

    @Override
    public void show(Context context) {
        context.sessionAttribute("yaExisteConEseNombre", null);
        String heladeraId = context.pathParam("heladeraId");
        Map<String, Object> model = heladeraService.mostrarHeladera(Long.valueOf(heladeraId));
        if (model != null) {
            context.render("heladeras/verHeladera.hbs", model);
        } else {
            context.status(404).result("Heladera no encontrada");
        }
    }

    @Override
    public void create(Context context) {
        context.sessionAttribute("yaExisteConEseNombre", null);
        HeladeraInputDTO heladeraInputDTO = new HeladeraInputDTO();
        heladeraInputDTO.setPiso(context.formParam("piso"));
        heladeraInputDTO.setCalle(context.formParam("calle"));
        heladeraInputDTO.setNombre(context.formParam("nombre"));
        heladeraInputDTO.setAltura(context.formParam("altura"));
        heladeraInputDTO.setModelo(context.formParam("modelo"));

        heladeraService.darDeAltaHeladera(heladeraInputDTO);

        context.redirect("/heladeras");
    }

    public void showMap(Context context) {
        context.sessionAttribute("yaExisteConEseNombre", null);
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarSesion(context, model);
        List<HeladeraOutputDTO> heladeras = heladeraService.obtenerHeladeras();
        model.put("heladeras", heladeras);
        context.render("heladeras/mapaHeladeras.hbs", model);
    }

    public void createModel(Context context) {
        ModeloDTO modeloDTO = new ModeloDTO();
        modeloDTO.setPeso(context.formParam("peso"));
        modeloDTO.setNombre(context.formParam("nombre"));
        modeloDTO.setTempMax(context.formParam("tempMax"));
        modeloDTO.setTempMin(context.formParam("tempMin"));
        modeloDTO.setCantidadViandas(context.formParam("cantidadViandas"));
        heladeraService.cargarModelo(modeloDTO);
        context.redirect("/heladeras");
    }

    @Override
    public void save(Context context) {
    }

    @Override
    public void edit(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.sessionAttribute("yaExisteConEseNombre", null);
        String heladeraId = context.formParam("id");
        HeladeraInputDTO heladeraInputDTO = new HeladeraInputDTO();
        heladeraInputDTO.setNombre(context.formParam("nombre"));
        heladeraInputDTO.setModelo(context.formParam("modelo"));
        heladeraInputDTO.setCalle(context.formParam("calle"));
        heladeraInputDTO.setAltura(context.formParam("altura"));
        heladeraInputDTO.setPiso(context.formParam("piso"));
        boolean modificacionRealizada = heladeraService.modificarHeladera(Long.parseLong(heladeraId), heladeraInputDTO);
        if (!modificacionRealizada) {
            // TODO AVISAR CON MENSAJE EN PANTALLA QUE YA ESTA ESE NOMBRE Y DEBE INTENTAR CON OTRO
            model.put("yaExisteConEseNombre", true);
        }
        context.redirect("/heladeras");
    }

    @Override
    public void update(Context context) {
    }

    @Override
    public void delete(Context context) {
        String heladeraId = context.formParam("heladeraId");
        heladeraService.darDeBajaHeladera(Long.parseLong(heladeraId));
        context.redirect("/heladeras");
    }
}

