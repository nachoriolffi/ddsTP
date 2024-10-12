package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.usuario.Permiso;
import ar.edu.utn.frba.dds.models.entities.usuario.Rol;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class CrearCuentaController  extends BaseController implements ICrudViewsHandler {

    RepoUsuario repoUsuario = RepoUsuario.INSTANCE;

    @Override
    public void index(Context context) {
        Map<String,Object> model = new HashMap<>();
        model.put("title","Crear Cuenta");
        context.render("logs/crearCuenta.hbs",model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

        String correoElectronico = context.formParam("email");
        String password = context.formParam("password");
        String confirmPassword = context.formParam("confirmPassword");
        String tipoUsuario = context.formParam("tipoUsuario");

        if (correoElectronico == null || password == null || confirmPassword == null) {
            context.status(400).result("All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            context.status(400).result("Passwords do not match.");
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setCorreoElectronico(correoElectronico);
        nuevoUsuario.setContrasenia(password);

        assert tipoUsuario != null;
        if(tipoUsuario.equals("Juridico")) {
            nuevoUsuario.setRol(TipoRol.COLABORADOR_JURIDICO);
            repoUsuario.agregar(nuevoUsuario);
            context.sessionAttribute("usuario_id", nuevoUsuario.getId());
            context.redirect("/registroJuridico");
        } else {
            nuevoUsuario.setRol(TipoRol.COLABORADOR_HUMANO);
            repoUsuario.agregar(nuevoUsuario);
            context.sessionAttribute("usuario_id", nuevoUsuario.getId());
            context.redirect("/registroHumano");
        }


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
