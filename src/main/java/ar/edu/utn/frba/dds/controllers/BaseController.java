package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import io.javalin.http.Context;

import java.util.*;

public abstract class BaseController {

    private static final Set<String> rutasValidas = new HashSet<>(Set.of(
            "/iniciarSesion",
            "/crearCuenta"
    ));

    private static final String RUTA_ERROR_403 = "/error403";
    private static final String RUTA_INICIO_SESION = "/iniciarSesion";

    protected Usuario usuarioLogueado(Context ctx) {
        Long usuarioId = ctx.sessionAttribute("usuario_id");
        if (usuarioId == null) {
            return null;
        }
        return RepoUsuario.INSTANCE.buscar(usuarioId);
    }

    protected Usuario verificarSesion(Context ctx, Map<String, Object> model) {
        Usuario usuario = usuarioLogueado(ctx);
        if (usuario != null) {
            configurarUsuarioLogueado(model, usuario);
            return usuario;
        } else {
            manejarSesionNoEncontrada(ctx, model);
            return null;
        }
    }

    private void configurarUsuarioLogueado(Map<String, Object> modelo, Usuario usuario) {
        modelo.put("inicioSesion", true);
        modelo.put("noInicioSesion", false);
        if (usuario.getRol().equals(TipoRol.ADMIN)) {
            modelo.put("esAdmin", true);
        } else if (usuario.getRol().equals(TipoRol.COLABORADOR_HUMANO)) {
            modelo.put("esHumano", true);
            modelo.put("noEsAdmin", true);
        } else if (usuario.getRol().equals(TipoRol.COLABORADOR_JURIDICO)) {
            modelo.put("esJuridico", true);
            modelo.put("noEsAdmin", true);
        }
    }

    private void manejarSesionNoEncontrada(Context ctx, Map<String, Object> modelo) {
        if (!rutasValidas.contains(ctx.path())) {
            ctx.redirect(RUTA_INICIO_SESION);
        }
        modelo.put("inicioSesion", false);
        modelo.put("noInicioSesion", true);
    }

    protected Usuario verificarRol(Context ctx, Map<String, Object> modelo, TipoRol... rolesPermitidos) {
        Usuario usuario = usuarioLogueado(ctx);
        if (usuario == null || !Arrays.asList(rolesPermitidos).contains(usuario.getRol())) {
            manejarSesionNoEncontrada(ctx, modelo);
            ctx.redirect(RUTA_ERROR_403);
            return null;
        }
        configurarUsuarioLogueado(modelo, usuario);
        return usuario;
    }

    protected Usuario verificarHumano(Context ctx, Map<String, Object> modelo) {
        return verificarRol(ctx, modelo, TipoRol.COLABORADOR_HUMANO);
    }

    protected Usuario verificarJuridicoOHumano(Context ctx, Map<String, Object> modelo) {
        return verificarRol(ctx, modelo, TipoRol.COLABORADOR_JURIDICO, TipoRol.COLABORADOR_HUMANO);
    }

    protected Usuario verificarJuridico(Context ctx, Map<String, Object> modelo) {
        return verificarRol(ctx, modelo, TipoRol.COLABORADOR_JURIDICO);
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

    protected Usuario verificarAdmin(Context ctx, Map<String, Object> modelo) {
        return verificarRol(ctx, modelo, TipoRol.ADMIN);
    }
}
