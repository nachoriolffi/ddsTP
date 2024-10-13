package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.RespuestaDTO;
import ar.edu.utn.frba.dds.dtos.PreguntaDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionarioRespondido;
import ar.edu.utn.frba.dds.services.RegistroHumanoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroHumanoController extends BaseController implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        Usuario usuario = usuarioLogueado(context);
        try {
            Cuestionario cuestionario = RepoCuestionario.INSTANCE.buscar(1L);
            if (cuestionario == null) {
                context.status(404).result("Cuestionario no encontrado");
            }
            Map<String, List<Pregunta>> categorizedQuestions = cuestionario.getPreguntas().stream()
                    .collect(Collectors.groupingBy(Pregunta::getTipoPregunta));

            Map<String, Object> model = new HashMap<>();
            model.put("title", "Registro Humano");
            model.put("usuario", usuario);
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
        CuestionarioRespondido cuestionarioRespondido = registroHumanosService.processAndSaveResponses(context);
        System.out.println(cuestionarioRespondido.getRespuestas().size());

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
