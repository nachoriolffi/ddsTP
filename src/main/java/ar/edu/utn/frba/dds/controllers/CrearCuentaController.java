package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.usuario.Permiso;
import ar.edu.utn.frba.dds.models.entities.usuario.Rol;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.validador.ValidadorDeContrasenia;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import ar.edu.utn.frba.dds.server.Server;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CrearCuentaController extends BaseController implements ICrudViewsHandler {

    RepoUsuario repoUsuario = RepoUsuario.INSTANCE;

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        verificarSesion(context, model);
        String faltaRellenar = context.sessionAttribute("faltaRellenar");
        String clavesNoCoinciden = context.sessionAttribute("clavesNoCoinciden");
        String tipoNoSeleccionado = context.sessionAttribute("tipoNoSeleccionado");
        String claveNoSegura = context.sessionAttribute("claveNoSegura");
        model.put("title", "Crear Cuenta");
        model.put("tipoNoSeleccionado", tipoNoSeleccionado);
        model.put("faltaRellenar", faltaRellenar);
        model.put("clavesNoCoinciden", clavesNoCoinciden);
        model.put("claveNoSegura", claveNoSegura);
        context.render("logs/crearCuenta.hbs", model);
    }

    @Override
    public void save(Context context) {

        context.sessionAttribute("tipoNoSeleccionado", null);
        context.sessionAttribute("faltaRellenar", null);
        context.sessionAttribute("clavesNoCoinciden", null);
        context.sessionAttribute("claveNoSegura", null);

        String correoElectronico = context.formParam("email");
        String password = context.formParam("password");
        String confirmPassword = context.formParam("confirmPassword");
        String tipoUsuario = context.formParam("tipoUsuario");

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setCorreoElectronico(correoElectronico);
        nuevoUsuario.setContrasenia(password);

        if (tipoUsuario == null || tipoUsuario.isEmpty()) {
            context.sessionAttribute("tipoNoSeleccionado", "Debe seleccionar un tipo de Colaborador (Juridico o Humano)");
            context.redirect("/crearCuenta");
            Server.registry.counter("tpdds.cuentasCreadas","status","noSeleccionoColaborador").increment();
            return;
        } else if (correoElectronico == null || password == null || confirmPassword == null) {
            context.sessionAttribute("faltaRellenar", "Algun campo no fue completado");
            context.redirect("/crearCuenta");
            Server.registry.counter("tpdds.cuentasCreadas","status","camposIncompletos").increment();
            return;
        } else if (!password.equals(confirmPassword)) {
            context.sessionAttribute("clavesNoCoinciden", "Las claves no coinciden. Intente de nuevo");
            context.redirect("/crearCuenta");
            Server.registry.counter("tpdds.cuentasCreadas","status","clavesNoCoinciden").increment();
            return;
        } else {
            ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
            validadorDeContrasenia.instanciarFormaValidar();
            boolean respuesta = validadorDeContrasenia.validarContrasenia(password, nuevoUsuario);
            if (!respuesta) {
                context.sessionAttribute("claveNoSegura", "La contraseña debe tener entre 8 y 64 caracteres y no debe estar entre las peores 10000");
                context.redirect("/crearCuenta");
                Server.registry.counter("tpdds.cuentasCreadas","status","contraseñaInsegura").increment();
                return;
            }

        }

        if (tipoUsuario.equals("Juridico")) {
            nuevoUsuario.setRol(TipoRol.COLABORADOR_JURIDICO);
            context.sessionAttribute("nuevoUsuario", nuevoUsuario);
            context.sessionAttribute("usuario_id", nuevoUsuario.getId());
            context.redirect("/registroJuridico");
            Server.registry.counter("tpdds.cuentasCreadas","status","Juridico").increment();
        } else {
            nuevoUsuario.setRol(TipoRol.COLABORADOR_HUMANO);
            context.sessionAttribute("nuevoUsuario", nuevoUsuario);
            context.sessionAttribute("usuario_id", nuevoUsuario.getId());
            context.redirect("/registroHumano");
            Server.registry.counter("tpdds.cuentasCreadas","status","Humano").increment();
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

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }
}