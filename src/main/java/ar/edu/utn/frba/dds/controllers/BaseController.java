package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import io.javalin.http.Context;

import java.util.Objects;

public abstract class BaseController {
    protected Usuario usuarioLogueado(Context ctx) {
        if (ctx.sessionAttribute("usuario_id") == null)
            return null;
        return RepoUsuario.INSTANCE.buscar((Long) Objects.requireNonNull(ctx.sessionAttribute("usuario_id")));


    }

}
