package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.validador.ValidadorDeContrasenia;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends BaseController implements ICrudViewsHandler {

    private final UserService userService = new UserService();

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarSesion(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);

        String clavesNoCoinciden = context.sessionAttribute("clavesNoCoinciden");
        String claveNoSegura = context.sessionAttribute("claveNoSegura");
        model.put("clavesNoCoinciden", clavesNoCoinciden);
        model.put("claveNoSegura", claveNoSegura);
        model.put("usuario", usuarioDTO);
        model.put("title", "Mi Perfil");
        context.render("perfil.hbs", model);
    }

    @Override
    public void edit(Context context) {

        context.sessionAttribute("clavesNoCoinciden", null);
        context.sessionAttribute("claveNoSegura", null);

        Usuario usuario = usuarioLogueado(context);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre(context.formParam("nombre"));
        usuarioDTO.setApellido(context.formParam("apellido"));
        usuarioDTO.setEmail(context.formParam("email"));
        usuarioDTO.setClave(context.formParam("clave"));
        usuarioDTO.setRazonSocial(context.formParam("razonSocial"));
        usuarioDTO.setTipoJurisdiccion(context.formParam("tipoJurisdiccion"));
        usuarioDTO.setDireccion(context.formParam("direccion"));
        usuarioDTO.setTelefono(context.formParam("telefono"));
        String confirmarClave = context.formParam("confirmarClave");

        if (userService.validarContrasenia(usuarioDTO.getClave(), confirmarClave)) {
            if (!userService.validarContraseniaSegura(usuarioDTO.getClave(), usuario)) {
                // Si la contraseña no es segura, mostrar un mensaje y no actualizar
                context.sessionAttribute("claveNoSegura", "La contraseña debe tener entre 8 y 64 caracteres y no debe estar entre las peores 10000.");
                context.redirect("/verPerfil");
            } else {
                // Si la contraseña es válida y segura, actualizar el usuario
                userService.actualizarUsuario(usuario, usuarioDTO);
                context.consumeSessionAttribute("usuario_id");
                context.consumeSessionAttribute("tipo_rol");
                context.redirect("/iniciarSesion");
            }
        } else {
            // Si las contraseñas no coinciden, mostrar un mensaje y no actualizar
            context.sessionAttribute("clavesNoCoinciden", "Las claves no coinciden.");
            context.redirect("/verPerfil");
        }

    }

    @Override
    public void update(Context context) {
    }

    @Override
    public void delete(Context context) {
    }

    @Override
    public void show(Context context) {
    }

    @Override
    public void create(Context context) {
    }

    @Override
    public void save(Context context) {
    }
}
