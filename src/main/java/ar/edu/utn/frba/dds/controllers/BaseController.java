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

    protected Usuario verificarSesion(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        if (usuario != null) {
            model.put("inicioSesion", true);
            model.put("noInicioSesion", false);
            if (usuario.getRol().equals(TipoRol.ADMIN)) {
                model.put("esAdmin", true);
            }else if (usuario.getRol().equals(TipoRol.COLABORADOR_HUMANO)) {
                model.put("noEsAdmin",true);
                model.put("esHumano", true);
            }else if (usuario.getRol().equals(TipoRol.COLABORADOR_JURIDICO)) {
                model.put("noEsAdmin",true);
                model.put("esJuridico", true);
            }else if (usuario.getRol().equals(TipoRol.TECNICO)) {
                model.put("noEsAdmin",true);
                model.put("esTecnico", true);
            }
            return  usuario;
        } else {
            if (!validPaths.contains(ctx.path())) {
                ctx.redirect("/iniciarSesion");
            }
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
        }
        return null;
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
            model.put("noEsAdmin",true);
            return usuario;

        } else if (usuario.getRol().equals(TipoRol.COLABORADOR_HUMANO)) {
            model.put("esHumano", true);
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
            model.put("noEsAdmin",true);
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
            model.put("noEsAdmin",false);
            return usuario;
        } else {
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
            ctx.redirect("/error403");
            return null;
        }
    }

    protected Usuario verificarTecnico(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        verificarSesion(ctx, model);
        if (usuario.getRol().equals(TipoRol.TECNICO)) {
            model.put("esTecnico", true);
            model.put("noEsAdmin",true);
            return usuario;
        } else {
            model.put("inicioSesion", false);
            model.put("noInicioSesion", true);
            ctx.redirect("/error403");
            return null;
        }
    }

}
