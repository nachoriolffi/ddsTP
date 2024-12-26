package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.services.RegistroHumanoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroJuridicoController extends BaseController implements ICrudViewsHandler {

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

            List<TipoColaboracion> formasDeColaboracion = Arrays.asList(TipoColaboracion.DINERO, TipoColaboracion.HACERSE_CARGO_HELADERA);

            Map<String, Object> model = new HashMap<>();
            model.put("formasDeColaboracion", formasDeColaboracion);
            model.put("title", "Registro Juridico");
            model.put("usuario", nuevoUsuario);
            model.put("cuestionario", cuestionario);
            model.put("preguntas", categorizedQuestions);
            model.put("noInicioSesion", true);
            context.render("logs/registroJuridico.hbs", model);
        } catch (Exception e) {
            context.status(500).result("Server error");
        }
    }

    @Override
    public void create(Context context) { // ESTE ES EL POSTA QUE USAMOS PARA LOS COLABORADORES
        Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        RegistroHumanoService registroHumanosService = new RegistroHumanoService();
        Colaborador colaborador = registroHumanosService.processAndSaveResponses(context);
        colaborador.getUsuario().setNombre(colaborador.getRazonSocial());
        colaborador.getUsuario().setCuentaEliminada(false);
        colaborador.setTipoPersona(TipoPersona.JURIDICA);
        Direccion direccion = new Direccion();
        direccion.setPiso(Integer.valueOf(context.formParam("piso")));
        Calle calle = new Calle();
        calle.setCalle(context.formParam("calle"));
        RepoCalle.INSTANCE.agregar(calle);
        direccion.setCalle(calle);
        //direccion.setAltura(Integer.valueOf(context.formParam("altura")));
        String alturaParam = context.formParam("altura");
        if (alturaParam != null && !alturaParam.isEmpty()) {
            direccion.setAltura(Integer.valueOf(alturaParam));
        } else {
            direccion.setAltura(0);
        }

        RepoDireccion.INSTANCE.agregar(direccion);
        colaborador.setDireccion(direccion);
        String[] formasDeColaboracion = context.formParams("colaboraciones").toArray(new String[0]);
        for (String forma : formasDeColaboracion) {
            colaborador.agregarFormaColaboracion(TipoColaboracion.valueOf(forma));
        }
        RepoUsuario.INSTANCE.agregar(nuevoUsuario);
        RepoColaborador.INSTANCE.agregar(colaborador);
        context.redirect("/iniciarSesion");
    }

    @Override
    public void save(Context context) throws ParseException {

        // ESTE METODO NO LO USAMOS, USAMOS EL QUE ESTA MAS ARRIBA
        /*Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        if (nuevoUsuario == null) {
            context.redirect("/crearCuenta");
            return;
        }

        String razonSocial = context.formParam("respuesta-1"); //"razon-social"
        String tipoRazonSocial = context.formParam("opcion-2").toUpperCase(); //"tipo-razon-social"

        nuevoUsuario.setNombre(razonSocial);

        Colaborador colaboradorJuridico = new Colaborador();
        colaboradorJuridico.setRazonSocial(razonSocial);
        colaboradorJuridico.setTipoPersona(TipoPersona.JURIDICA);
        colaboradorJuridico.setFueCargaMasiva(false);
        Direccion direccion = new Direccion();
        direccion.setAltura(Integer.valueOf(context.formParam("altura")));
        Calle calle = new Calle(context.formParam("calle"));
        RepoCalle.INSTANCE.agregar(calle);
        direccion.setCalle(calle);
        direccion.setPiso(Integer.valueOf(context.formParam("piso")));

        RepoDireccion.INSTANCE.agregar(direccion);

        int tRazonSocialIndex = Integer.parseInt(tipoRazonSocial);
        colaboradorJuridico.setTipoJuridiccion(TipoJuridiccion.values()[tRazonSocialIndex]);

        repoUsuario.agregar(nuevoUsuario);
        repoColaborador.agregar(colaboradorJuridico);

        context.redirect("/iniciarSesion");*/
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
}
