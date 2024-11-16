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
        
        RepoColaborador.INSTANCE.agregar(colaborador);

        if(colaborador.getFormasDeColaboracion().contains(TipoColaboracion.DONACION_VIANDAS) || colaborador.getFormasDeColaboracion().contains(TipoColaboracion.REDISTRIBUCION_VIANDAS)){
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setColaboradorAsignador(colaborador);
            tarjeta.setFechaRegistro(new Date());
            RepoTarjeta.INSTANCE.agregar(tarjeta);
        }

        //context.redirect("/iniciarSesion");
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
