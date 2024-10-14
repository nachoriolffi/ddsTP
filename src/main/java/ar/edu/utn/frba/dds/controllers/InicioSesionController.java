package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.usuario.Rol;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InicioSesionController extends BaseController implements ICrudViewsHandler  {
    @Override
    public void index(Context ctx) {

        Map<String, Object> model = new HashMap<>();
        verificarSesion(ctx, model);
        String loginError = ctx.sessionAttribute("loginError");
        model.put("title", "Iniciar Sesión");
        model.put("loginError", loginError);

        ctx.render("logs/inicioSesion.hbs", model);
    }

    public void login(Context ctx) {
        ctx.sessionAttribute("loginError", null); // Limpia el mensaje de error de la sesión
        String email = ctx.formParam("correoElectronico");
        String password = ctx.formParam("clave");

        Usuario usuario = RepoUsuario.INSTANCE.buscarPorEmail(email).orElse(null);

        if (usuario == null || !usuario.getContrasenia().equals(password)) {
            ctx.sessionAttribute("loginError", "Usuario o Clave Incorrectas");
            ctx.redirect("/iniciarSesion");
        }else{
            // TODO TENEMOS QUE HACER QUE SE VAYANA A PANTALLAS MAIN DE CADA UNO
            // TODO TIPO QUE DIGAN TODO LO PUEDAN HACER CON BOTONES COMO UN PANEL DE CONTROL
            // TODO EN VEZ DE HACER REDIRECT A ESA PAGINAS QUE NO TIENEN MUCHO QUE VER
            ctx.sessionAttribute("usuario_id", usuario.getId());
            if (usuario.getRol().equals(TipoRol.COLABORADOR_JURIDICO)) {
                ctx.redirect("/misHeladeras");
            }else if (usuario.getRol().equals(TipoRol.ADMIN)){
                ctx.redirect("/reportes");
            }
        }
    }
    public void logout(Context ctx){
        ctx.consumeSessionAttribute("usuario_id");
        ctx.consumeSessionAttribute("tipo_rol");
        ctx.redirect("/iniciarSesion");
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


