package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.services.ColaboradorService;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColaboradorController extends BaseController implements ICrudViewsHandler {

    private final ColaboradorService colaboradorService = new ColaboradorService();
    UserService userService = new UserService();

    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarAdmin(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        List<ColaboradorDTO> colaboradorDTOS = colaboradorService.obtenerColaboradores();
        model.put("colaboradores", colaboradorDTOS);
        model.put("title", "Colaboradores");
        context.render("colaboradores.hbs", model);
    }

    @Override
    public void delete(Context context) {
        String idColaborador = context.formParam("colaboradorId");
        if (idColaborador != null) {
            colaboradorService.darDeBaja(Long.parseLong(idColaborador));
        }
        context.redirect("/verColaboradores");
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
}
