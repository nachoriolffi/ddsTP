package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import io.javalin.http.Context;

import java.util.*;

public abstract class BaseController {

    protected Usuario usuarioLogueado(Context ctx) {
        if (ctx.sessionAttribute("usuario_id") == null) {
            return null;
        }
        return RepoUsuario.INSTANCE.buscar((Long) Objects.requireNonNull(ctx.sessionAttribute("usuario_id")));
    }

    Set<String> validPaths = new HashSet<>(Set.of(
            "/iniciarSesion",
            "/crearCuenta"
    ));

    Set<String> validPathsColaborador = new HashSet<>(Set.of(
            "/registroJuridico",
            "/registroHumano"
    ));

    protected void verificarSesion(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        if (usuario != null) {
            model.put("inicioSesion", true);
            model.put("noInicioSesion", false);
        } else {
            // Si no está logueado, exceptúa las rutas de iniciar sesión y registrarse
            if (!validPaths.contains(ctx.path())) {
                ctx.redirect("/iniciarSesion");
            }
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
        }


    }

    protected Usuario verificarHumano(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        verificarSesion(ctx, model);
        if (usuario.getRol().equals(TipoRol.COLABORADOR_HUMANO)) {
            model.put("esHumano", true);
            return usuario;
        } else {
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
            ctx.redirect("/error403");
            return null;
        }
    }

    protected Usuario verificarJuridicoOHumano(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        verificarSesion(ctx, model);
        if (usuario.getRol().equals(TipoRol.COLABORADOR_JURIDICO)) {
            model.put("esJuridico", true);
            System.out.println("es juridico");
            return usuario;

        } else if (usuario.getRol().equals(TipoRol.COLABORADOR_HUMANO)) {
            model.put("esHumano", true);
            System.out.println("es humano");
            return usuario;
        } else {
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
            ctx.redirect("/error403");
            System.out.println("No es juridico ni humano");
            return null;
        }
    }

    protected Usuario verificarJuridico(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        verificarSesion(ctx, model);
        if (usuario.getRol().equals(TipoRol.COLABORADOR_JURIDICO)) {
            model.put("esJuridico", true);
            return usuario;
        } else {
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
            ctx.redirect("/error403");
            return null;
        }
    }

    protected Usuario verificarAdmin(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        verificarSesion(ctx, model);
        if (usuario.getRol().equals(TipoRol.ADMIN)) {
            model.put("esAdmin", true);
            return usuario;
        } else {
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
            ctx.redirect("/error403");
            return null;
        }
    }

}
