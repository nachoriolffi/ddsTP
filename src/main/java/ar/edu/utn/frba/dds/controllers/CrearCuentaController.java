package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.usuario.Permiso;
import ar.edu.utn.frba.dds.models.entities.usuario.Rol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class CrearCuentaController  extends BaseController implements ICrudViewsHandler {
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

        // Validate the parameters
        if (correoElectronico == null || password == null || confirmPassword == null) {
            context.status(400).result("All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            context.status(400).result("Passwords do not match.");
            return;
        }

        // Create a new user account
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setCorreoElectronico(correoElectronico);
        nuevoUsuario.setContrasenia(password);
        //RepoRol rol =
        //nuevoUsuario.setRol(rol);
        try {
            // Save the new user to the repository
            RepoUsuario.INSTANCE.agregar(nuevoUsuario);

            Colaborador nuevoColaborador = new Colaborador();
            nuevoColaborador.setUsuario(nuevoUsuario);

            assert tipoUsuario != null;
            if(tipoUsuario.equals("Juridico")) {
                nuevoColaborador.setTipoPersona(TipoPersona.JURIDICA);
            } else {
                nuevoColaborador.setTipoPersona(TipoPersona.HUMANA);
            }

            RepoColaborador.INSTANCE.agregar(nuevoColaborador);

            // Redirect to a success page or show a success message
            context.redirect("/inicioSesion");
        } catch (Exception e) {
            context.status(400).result("Email already exists.");
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
