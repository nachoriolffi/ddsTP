package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.RespuestaDTO;
import ar.edu.utn.frba.dds.dtos.PreguntaDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionarioRespondido;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;
import ar.edu.utn.frba.dds.services.RegistroHumanoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.*;
import java.util.stream.Collectors;

public class RegistroHumanoController extends BaseController implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        if (nuevoUsuario == null) {
            context.redirect("/crearCuenta");
            return;
        }
        try {
            Cuestionario cuestionario = RepoCuestionario.INSTANCE.buscar(2L);
            if (cuestionario == null) {
                context.status(404).result("Cuestionario no encontrado");
            }
            Map<String, List<Pregunta>> categorizedQuestions = cuestionario.getPreguntas().stream()
                    .collect(Collectors.groupingBy(Pregunta::getTipoPregunta));

            List<TipoColaboracion> formasDeColaboracion = Arrays.asList(TipoColaboracion.values());

            Map<String, Object> model = new HashMap<>();
            model.put("formasDeColaboracion", formasDeColaboracion);
            model.put("title", "Registro Humano");
            model.put("usuario", nuevoUsuario);
            model.put("cuestionario", cuestionario);
            model.put("preguntas", categorizedQuestions);
            context.render("logs/registroHumano.hbs", model);
        } catch (Exception e) {
            e.printStackTrace();
            context.status(500).result("Server error");
        }
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

        RegistroHumanoService registroHumanosService = new RegistroHumanoService();
        Colaborador colaborador = registroHumanosService.processAndSaveResponses(context);
        colaborador.setTipoPersona(TipoPersona.HUMANA);

        String[] formasDeColaboracion = context.formParams("colaboraciones").toArray(new String[0]);
        for (String forma : formasDeColaboracion) {
            colaborador.agregarFormaColaboracion(TipoColaboracion.valueOf(forma));
        }

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
        colaborador.setContacto(contacto);

        RepoColaborador.INSTANCE.agregar(colaborador); // lo pongo antes porque para persistir la tarjeta el colabordor ya debe estar persistido

        if(colaborador.getFormasDeColaboracion().contains(TipoColaboracion.DONACION_VIANDAS) || colaborador.getFormasDeColaboracion().contains(TipoColaboracion.REDISTRIBUCION_VIANDAS)){
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setColaboradorAsignador(colaborador);
            tarjeta.setFechaRegistro(new Date());
            RepoTarjeta.INSTANCE.agregar(tarjeta);
        }

        context.redirect("/iniciarSesion");
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
