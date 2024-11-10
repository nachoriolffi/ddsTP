package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
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
import ar.edu.utn.frba.dds.services.RegistroHumanoService;
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

        Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        if (nuevoUsuario == null) {
            context.redirect("/crearCuenta");
            return;
        }
        try {
            Cuestionario cuestionario = RepoCuestionario.INSTANCE.buscar(1L);
            if (cuestionario == null) {
                context.status(404).result("Cuestionario no encontrado");
            }
            Map<String, List<Pregunta>> categorizedQuestions = cuestionario.getPreguntas().stream()
                    .collect(Collectors.groupingBy(Pregunta::getTipoPregunta));

            List<TipoColaboracion> formasDeColaboracion = Arrays.asList(TipoColaboracion.values());

            Map<String, Object> model = new HashMap<>();
            model.put("formasDeColaboracion", formasDeColaboracion);
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

        String razonSocial = context.formParam("respuesta-1" ); //"razon-social"
        String tipoRazonSocial = context.formParam("opcion-2").toUpperCase(); //"tipo-razon-social"




        nuevoUsuario.setNombre(razonSocial);

        Colaborador colaboradorJuridico = new Colaborador();
        colaboradorJuridico.setRazonSocial(razonSocial);
        colaboradorJuridico.setTipoPersona(TipoPersona.JURIDICA);

        Integer tRazonSocialIndex = Integer.parseInt(tipoRazonSocial);
        colaboradorJuridico.setTipoJuridiccion(TipoJuridiccion.values()[tRazonSocialIndex]);

//        String altura = context.formParam("altura");
//        String piso = context.formParam("piso");
//        String calle = context.formParam("calle");

//        if (piso != null && !piso.isEmpty() && altura != null && !altura.isEmpty() && calle != null && !calle.isEmpty()) {
//            Direccion direccion = new Direccion();
//            direccion.setPiso(Integer.valueOf(piso));
//            direccion.setAltura(Integer.valueOf(altura));
//            direccion.setCalle(new Calle(calle));
//            repoDireccion.agregar(direccion);
//            colaboradorJuridico.setDireccion(direccion);
//        }

        String telefono = context.formParam("telefono");
        String correo = context.formParam("correo");
        List<Contacto> contacto = new ArrayList<>();

        List<String> mediosContacto = context.formParams("medios-contacto");
        // Verificar si se seleccionaron "WhatsApp" o "Telegram"
        boolean seleccionoWhatsApp = mediosContacto.contains("whatsapp");
        boolean seleccionoTelegram = mediosContacto.contains("telegram");


        if (telefono != null && !telefono.isEmpty()) {
            /*if (seleccionoWhatsApp) {
                Contacto contactoWhatsApp = new Contacto(TipoContacto.WPP, telefono);
                contacto.add(contactoWhatsApp);
            }*/ // lo dejo comentado para no enviar todavía notificaciones por whatsapp y no gastar recursos
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
        context.redirect("/iniciarSesion");

       //RegistroHumanoService registroHumanosService = new RegistroHumanoService();
        //Colaborador colaborador = registroHumanosService.processAndSaveResponses(context);
        //colaborador.setTipoPersona(TipoPersona.JURIDICA);
        //RepoColaborador.INSTANCE.agregar(colaborador);
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
        RegistroHumanoService registroHumanosService = new RegistroHumanoService();
        Colaborador colaborador = registroHumanosService.processAndSaveResponses(context);
        colaborador.setTipoPersona(TipoPersona.JURIDICA);

        String[] formasDeColaboracion = context.formParams("colaboraciones").toArray(new String[0]);
        for (String forma : formasDeColaboracion) {
            colaborador.agregarFormaColaboracion(TipoColaboracion.valueOf(forma));
        }

        RepoColaborador.INSTANCE.agregar(colaborador);
    }
}
