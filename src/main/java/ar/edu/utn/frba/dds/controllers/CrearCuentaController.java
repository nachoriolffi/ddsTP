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
        Map<String, Object> model = new HashMap<>();
        String faltaRellenar = context.sessionAttribute("faltaRellenar");
        String clavesNoCoinciden = context.sessionAttribute("clavesNoCoinciden");
        String tipoNoSeleccionado = context.sessionAttribute("tipoNoSeleccionado");
        model.put("title", "Crear Cuenta");
        model.put("tipoNoSeleccionado", tipoNoSeleccionado);
        model.put("faltaRellenar", faltaRellenar);
        model.put("clavesNoCoinciden", clavesNoCoinciden);
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

        context.sessionAttribute("tipoNoSeleccionado", null);
        context.sessionAttribute("faltaRellenar", null);
        context.sessionAttribute("clavesNoCoinciden", null);

        String correoElectronico = context.formParam("email");
        String password = context.formParam("password");
        String confirmPassword = context.formParam("confirmPassword");
        String tipoUsuario = context.formParam("tipoUsuario");

        if (tipoUsuario == null || tipoUsuario.isEmpty()) {
            context.sessionAttribute("tipoNoSeleccionado", "Debe seleccionar un tipo de Colaborador (Juridico o Humano)");
            context.redirect("/crearCuenta");
            return;
        } else if (correoElectronico == null || password == null || confirmPassword == null) {
            context.sessionAttribute("faltaRellenar", "Algun campo no fue completado");
            context.redirect("/crearCuenta");
            return;
        } else if (!password.equals(confirmPassword)) {
            context.sessionAttribute("clavesNoCoinciden", "Las claves no coinciden. Intente de nuevo");
            context.redirect("/crearCuenta");
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setCorreoElectronico(correoElectronico);
        nuevoUsuario.setContrasenia(password);

        nuevoUsuario.setNombre("nombreHarcodeado");//harcodeo el nombre para poder probar

        assert tipoUsuario != null;
        if(tipoUsuario.equals("Juridico")) {
            nuevoUsuario.setRol(TipoRol.COLABORADOR_JURIDICO);
            context.sessionAttribute("nuevoUsuario", nuevoUsuario);
            context.sessionAttribute("usuario_id", nuevoUsuario.getId());
            context.redirect("/registroJuridico");
        } else {
            nuevoUsuario.setRol(TipoRol.COLABORADOR_HUMANO);
            context.sessionAttribute("nuevoUsuario", nuevoUsuario);
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