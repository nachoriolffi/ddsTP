package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.dtos.ViandaDTO;
import ar.edu.utn.frba.dds.dtos.outputs.DistribucionViandaOutputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoViandas;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.utn.frba.dds.services.SuscripcionesAHeladerasService;

public class suscripcionesAHeladerasController extends BaseController implements ICrudViewsHandler {
    SuscripcionesAHeladerasService suscripcionesAHeladerasService = new SuscripcionesAHeladerasService();
    UserService userService = new UserService();

    @Override
    public void index(Context context) {
//dejar heladera sugerida y desuscribirme para después
// los datos de la suscripcion están en ObserverColaborador
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarHumano(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        List<Heladera> heladeras = suscripcionesAHeladerasService.buscarHeladeras(usuario);

        try {
            verificarJuridicoOHumano(context, model);
            model.put("title", "Suscripciones A Heladeras");

            context.render("heladeras/suscripcionesAHeladeras.hbs", model);
        } catch (Exception e) {
            context.redirect("/iniciarSesion");
        }
    }

    @Override
    public void show(Context context) {

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
