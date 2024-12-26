package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.generadorCodigo.GeneradorDeCodigo;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.services.RegistroHumanoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.*;
import java.util.stream.Collectors;

public class RegistroHumanoController extends BaseController implements ICrudViewsHandler {

    RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
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
            model.put("title", "Registro Humano");
            model.put("usuario", nuevoUsuario);
            model.put("cuestionario", cuestionario);
            model.put("preguntas", categorizedQuestions);
            model.put("noInicioSesion", true);
            context.render("logs/registroHumano.hbs", model);
        } catch (Exception e) {
            context.status(500).result("Server error");
        }
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        RegistroHumanoService registroHumanosService = new RegistroHumanoService();
        Colaborador colaborador = registroHumanosService.processAndSaveResponses(context);
        colaborador.setTipoPersona(TipoPersona.HUMANA);
        colaborador.getUsuario().setNombre(colaborador.getNombre());
        colaborador.getUsuario().setApellido(colaborador.getApellido());
        colaborador.getUsuario().setCuentaEliminada(false);
        colaborador.getUsuario().setRol(TipoRol.COLABORADOR_HUMANO);
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

        if (colaborador.getFormasDeColaboracion().contains(TipoColaboracion.DONACION_VIANDAS) || colaborador.getFormasDeColaboracion().contains(TipoColaboracion.REDISTRIBUCION_VIANDAS)) {
            Tarjeta tarjeta = new Tarjeta();
            String codigo = GeneradorDeCodigo.getInstance().generarCodigoUnico();
            tarjeta.setCodigo(codigo);
            tarjeta.setColaboradorAsignador(colaborador);
            tarjeta.setFechaRegistro(new Date());
            RepoTarjeta.INSTANCE.agregar(tarjeta);
        }

        context.redirect("/iniciarSesion");
    }


    @Override
    public void save(Context context) {
        Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        if (nuevoUsuario == null) {
            context.redirect("/crearCuenta");
            return;
        }

        String nombre = context.formParam("respuesta-1");
        String apellido = context.formParam("respuesta-2");

        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);

        Colaborador colaboradorHumano = new Colaborador();
        colaboradorHumano.setNombre(nombre);
        colaboradorHumano.setApellido(apellido);
        colaboradorHumano.setTipoPersona(TipoPersona.HUMANA);
        colaboradorHumano.setUsuario(nuevoUsuario);

        List<Contacto> contacto = new ArrayList<>();

        colaboradorHumano.setContacto(contacto);

        repoUsuario.agregar(nuevoUsuario);
        repoColaborador.agregar(colaboradorHumano);
        context.redirect("/iniciarSesion");
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
