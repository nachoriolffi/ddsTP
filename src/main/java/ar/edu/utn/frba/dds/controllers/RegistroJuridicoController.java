package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDireccion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import ar.edu.utn.frba.dds.models.entities.contacto.*;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class RegistroJuridicoController extends BaseController implements ICrudViewsHandler {

    RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
    RepoDireccion repoDireccion = RepoDireccion.INSTANCE;
    RepoUsuario repoUsuario = RepoUsuario.INSTANCE;

    @Override
    public void index(Context context) {
        /*Usuario usuario = usuarioLogueado(context);
        if (usuario == null) {
            context.redirect("/inicioSesion");
        } else if (!usuario.getRol().equals(TipoRol.COLABORADOR_JURIDICO)) {
            context.redirect("/error403");
        }
        else{
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Registro Juridico");

            context.render("logs/registroJuridico.hbs",model);
        }*/


        Map<String, Object> model = new HashMap<>();
        Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        if (nuevoUsuario == null) {
            context.redirect("/crearCuenta");
            return;
        }
        try {
            Cuestionario cuestionario = RepoCuestionario.INSTANCE.buscar(2L); //los cuestionarios se cargan en el init al inicio del proghrama de ultima despues agregamos que busque el cuestionario mas viejo
            if (cuestionario == null) {
                context.status(404).result("Cuestionario no encontrado");
            }
            Map<String, List<Pregunta>> categorizedQuestions = cuestionario.getPreguntas().stream()
                    .collect(Collectors.groupingBy(Pregunta::getTipoPregunta));

            model.put("title", "Registro Juridico");
            model.put("usuario", nuevoUsuario);
            model.put("cuestionario", cuestionario);
            model.put("preguntas", categorizedQuestions);
            context.render("logs/registroJuridico.hbs", model);
        } catch (Exception e) {
            e.printStackTrace();
            context.status(500).result("Server error");
        }
    }

    @Override
    public void save(Context context) throws ParseException {
        Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        if (nuevoUsuario == null) {
            context.redirect("/crearCuenta");
            return;
        }
        String razonSocial = context.formParam("razon-social");
        String tipoRazonSocial = Objects.requireNonNull(context.formParam("tipo-razon-social")).toUpperCase();

        nuevoUsuario.setNombre(razonSocial);

        Colaborador colaboradorJuridico = new Colaborador();
        colaboradorJuridico.setRazonSocial(razonSocial);
        colaboradorJuridico.setTipoPersona(TipoPersona.JURIDICA);
        colaboradorJuridico.setTipoJuridiccion(TipoJuridiccion.valueOf(tipoRazonSocial));

        String altura = context.formParam("altura");
        String piso = context.formParam("piso");
        String calle = context.formParam("calle");

        if (piso != null && !piso.isEmpty() && altura != null && !altura.isEmpty() && calle != null && !calle.isEmpty()) {
            Direccion direccion = new Direccion();
            direccion.setPiso(Integer.valueOf(piso));
            direccion.setAltura(Integer.valueOf(altura));
            direccion.setCalle(new Calle(calle));
            repoDireccion.agregar(direccion);
            colaboradorJuridico.setDireccion(direccion);
        }

        String telefono = context.formParam("telefono");
        String correo = context.formParam("correo");
        List<Contacto> contacto = new ArrayList<>();

        List<String> mediosContacto = context.formParams("medios-contacto");
        // Verificar si se seleccionaron "WhatsApp" o "Telegram"
        boolean seleccionoWhatsApp = mediosContacto.contains("whatsapp");
        boolean seleccionoTelegram = mediosContacto.contains("telegram");


        if (telefono != null && !telefono.isEmpty()) {
            if (seleccionoWhatsApp) {
                Contacto contactoWhatsApp = new Contacto(TipoContacto.WPP, telefono);
                contacto.add(contactoWhatsApp);
            }
            if (seleccionoTelegram) {
                Contacto contactoTelegram = new Contacto(TipoContacto.TELEGRAM, telefono);
                contacto.add(contactoTelegram);
            }
        }
        if (correo != null && !correo.isEmpty()) {
            Contacto contactoCorreo = new Contacto(TipoContacto.MAIL, correo);
            contacto.add(contactoCorreo);
        }
        colaboradorJuridico.setContacto(contacto);
        repoUsuario.agregar(nuevoUsuario);
        repoColaborador.agregar(colaboradorJuridico);

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
