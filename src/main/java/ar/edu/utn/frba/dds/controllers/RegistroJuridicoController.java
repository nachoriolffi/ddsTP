package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import ar.edu.utn.frba.dds.models.entities.contacto.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RegistroJuridicoController extends BaseController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {
        Usuario usuario = usuarioLogueado(context);
        if (usuario == null) {
            context.redirect("/inicioSesion");
        } else if (!usuario.getRol().equals(TipoRol.COLABORADOR_JURIDICO)) {
            context.redirect("/error403");
        }
        else{
            context.render("logs/registroJuridico.hbs");
        }

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws ParseException {
        //si llega acá es porque pasó el chequeo de inicio de sesión y es un colaborador juridico
        // Logging form parameters
        System.out.println("razon-social: " + context.formParam("razon-social"));
        System.out.println("tipo-razon-social: " + context.formParam("tipo-razon-social"));
        System.out.println("calle: " + context.formParam("calle"));
        System.out.println("altura: " + context.formParam("altura"));
        System.out.println("piso: " + context.formParam("piso"));
        System.out.println("telefono: " + context.formParam("telefono"));
        System.out.println("correo: " + context.formParam("correo"));

        Usuario usuario = usuarioLogueado(context);
        try {
            RepoColaborador repoColaborador = new RepoColaborador();

            Colaborador colaboradorJuridico = new Colaborador();

            colaboradorJuridico.setUsuario(usuario);
            colaboradorJuridico.setRazonSocial(context.formParam("razon-social"));
            colaboradorJuridico.setTipoPersona(TipoPersona.JURIDICA);

            String razonSocial = context.formParam("tipo-razon-social").toUpperCase();
            colaboradorJuridico.setTipoJuridiccion(TipoJuridiccion.valueOf(razonSocial));

            Direccion direccion = new Direccion();

            String altura = context.formParam("altura");
            String piso = context.formParam("piso");
            String calle = context.formParam("calle");

            if (piso != null && !piso.isEmpty() || altura != null && !altura.isEmpty() || calle != null && !calle.isEmpty()) {
                if (piso != null && !piso.isEmpty()) {
                    direccion.setPiso(Integer.valueOf(context.formParam("piso")));
                }
                if (altura != null && !altura.isEmpty()) {
                    direccion.setAltura(Integer.valueOf(context.formParam("altura")));
                }
                if (calle != null && !calle.isEmpty()) {
                    direccion.setCalle(new Calle(calle));
                }
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

            repoColaborador.agregar(colaboradorJuridico);

        } catch (Exception e) {
            e.printStackTrace();
            context.status(500).result("Server error");
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
