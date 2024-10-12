package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.RespuestaDTO;
import ar.edu.utn.frba.dds.dtos.PreguntaDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionario;
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
                return;
            }
            List<PreguntaDTO> preguntasDTO = cuestionario.getPreguntas().stream()
                    .map(PreguntaDTO::new)
                    .toList();
            // Procesa todos los datos necesarios antes de cerrar la conexión
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Registro Humano");
            model.put("preguntas", preguntasDTO);
            context.render("logs/registroHumano.hbs", model);
        } catch (Exception e) {
            // Imprimir el stacktrace en los logs
            e.printStackTrace();
            context.status(500).result("Server error");
        }
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

        Map<String, String> params = context.formParamMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(0)));

        RespuestaDTO respuestaDTO = new RespuestaDTO();

        // Process open-ended responses
        respuestaDTO.setRespuestasAbiertas(params.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("textoPregunta"))
                .collect(Collectors.toMap(
                        entry -> Long.parseLong(entry.getKey().replace("textoPregunta", "")),
                        Map.Entry::getValue
                )));

        // Process single-choice responses
        respuestaDTO.setRespuestasUnicas(params.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("opcionesPregunta"))
                .collect(Collectors.toMap(
                        entry -> Long.parseLong(entry.getKey().replace("opcionesPregunta", "")),
                        entry -> Long.parseLong(entry.getValue())
                )));

        // Process multiple-choice responses
        respuestaDTO.setRespuestasMultiples(params.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("opcionesMultiplesPregunta"))
                .collect(Collectors.toMap(
                        entry -> Long.parseLong(entry.getKey().replace("opcionesMultiplesPregunta", "")),
                        entry -> Arrays.stream(entry.getValue().split(","))
                                .map(Long::parseLong)
                                .collect(Collectors.toList())
                )));

        // Process date responses
        respuestaDTO.setRespuestasFechas(params.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("fechaPregunta"))
                .collect(Collectors.toMap(
                        entry -> Long.parseLong(entry.getKey().replace("fechaPregunta", "")),
                        Map.Entry::getValue
                )));

        // Save the responses (implement your own logic here)
        // saveResponses(respuestaDTO);

        // Create the collaborator and save the responses
        Colaborador colaborador = new Colaborador();
        CuestionarioRespondido cuestionarioRespondido = new CuestionarioRespondido();
        // Add logic to save the responses to the `cuestionarioRespondido`
        // and associate it with the `colaborador`

        // Redirect or respond as needed
        context.redirect("/success");
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
