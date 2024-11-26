package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import ar.edu.utn.frba.dds.server.Server;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class InicioSesionController extends BaseController implements ICrudViewsHandler {

    @Override
    public void index(Context ctx) {

        Map<String, Object> model = new HashMap<>();

        verificarSesion(ctx, model);
        String loginError = ctx.sessionAttribute("loginError");
        model.put("title", "Iniciar Sesión");
        model.put("loginError", loginError);

        ctx.render("logs/iniciarSesion.hbs", model);
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

    public void login(Context ctx) {
        ctx.sessionAttribute("loginError", null); // Limpia el mensaje de error de la sesión
        String email = ctx.formParam("correoElectronico");
        String password = ctx.formParam("clave");

        Usuario usuario = RepoUsuario.INSTANCE.buscarPorEmail(email).orElse(null);

        if (usuario == null || !usuario.getContrasenia().equals(password)) {
            ctx.sessionAttribute("loginError", "Usuario o Clave Incorrectas");
            ctx.redirect("/iniciarSesion");
            Server.registry.counter("tpdds.inicioSesion","status","usuarioOClaveIncorrectas").increment();
        } else {
            ctx.sessionAttribute("usuario_id", usuario.getId());
            if (usuario.getRol().equals(TipoRol.COLABORADOR_JURIDICO)) {
                ctx.redirect("/verPerfil");
                Server.registry.counter("tpdds.inicioSesion","status","colabJuridico").increment();
            } else if (usuario.getRol().equals(TipoRol.ADMIN)) {
                ctx.redirect("/verPerfil");
                Server.registry.counter("tpdds.inicioSesion","status","admin").increment();
            } else if (usuario.getRol().equals(TipoRol.COLABORADOR_HUMANO)) {
                ctx.redirect("/verPerfil");
                Server.registry.counter("tpdds.inicioSesion","status","colabHumano").increment();
            }else if(usuario.getRol().equals(TipoRol.TECNICO)){
                ctx.redirect("/perfilTecnico");
                Server.registry.counter("tpdds.inicioSesion","status","tecnico").increment();
            }
        }
    }

    public void logout(Context ctx) {
        ctx.consumeSessionAttribute("usuario_id");
        ctx.consumeSessionAttribute("tipo_rol");
        ctx.redirect("/iniciarSesion");
    }

}


