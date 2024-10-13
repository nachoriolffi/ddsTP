package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class BaseController {

    protected Usuario usuarioLogueado(Context ctx) {
        if (ctx.sessionAttribute("usuario_id") == null) {
            return null;
        }
        return RepoUsuario.INSTANCE.buscar((Long) Objects.requireNonNull(ctx.sessionAttribute("usuario_id")));
    }

    protected Usuario verificarSesion(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        if (usuario != null) {
            model.put("inicioSesion", true);
            model.put("noInicioSesion", false);
        } else {
            if (!ctx.path().equals("/inicioSesion")) {
                ctx.redirect("/inicioSesion");
            }
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
        }
        return usuario;

    }

    protected Usuario verificarHumano(Context ctx, Map<String, Object> model) {
        Usuario usuario = verificarSesion(ctx, model);
        if (usuario.getRol().equals(TipoRol.COLABORADOR_HUMANO)) {
            model.put("esHumano", true);
            return usuario;
        }
        return null;
    }

    protected Usuario verificarJuridico() {
        return null;
    }

    protected Usuario verificarAdmin(Context ctx, Map<String, Object> model) {
        Usuario usuario = verificarSesion(ctx, model);
        if (usuario.getRol().equals(TipoRol.ADMIN)) {
            model.put("esAdmin", true);
            return usuario;
        }
        return null;
    }

}
