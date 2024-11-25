package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class MisHeladerasController extends BaseController implements ICrudViewsHandler {

    UserService userService = new UserService();
    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        try{
            Usuario usuario = verificarJuridicoOHumano(context, model);
            model.put("title", "Mis Heladeras");
            UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
            model.put("usuario", usuarioDTO);
            context.render("donaciones/misHeladeras.hbs", model);
        }
        catch (Exception e){
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
