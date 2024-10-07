package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import io.javalin.http.Context;

import java.util.Objects;

public abstract class BaseController {
    protected Usuario usuarioLogueado(Context ctx) {
        if (ctx.sessionAttribute("usuario_id") == null)
            return null;

        System.out.println("usuario_id: " + ctx.sessionAttribute("usuario_id"));
        Object usuarioIdObj = ctx.sessionAttribute("usuario_id");
        if (usuarioIdObj instanceof Integer) {
            String usuarioIdStr = String.valueOf(usuarioIdObj);
            try {
                Long usuarioId = Long.parseLong(usuarioIdStr);
                // Usa usuarioId como necesites
            } catch (NumberFormatException e) {
                ctx.status(400).result("Formato de Usuario ID no válido");
            }
        } else {
            ctx.status(400).result("Usuario ID no es un String");
        }
        return null;
    }
}
