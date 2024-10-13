package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionarioRespondido;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoPregunta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRespuesta;
import io.javalin.http.Context;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroHumanoService {

    RepoPregunta repoPregunta = RepoPregunta.INSTANCE;
    RepoRespuesta repoRespuesta = RepoRespuesta.INSTANCE;
    public CuestionarioRespondido processAndSaveResponses(Context context) {
    Map<String, String> params = context.formParamMap().entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(0)));

    CuestionarioRespondido cuestionarioRespondido = new CuestionarioRespondido();
    RepoCuestionarioRespondido.INSTANCE.agregar(cuestionarioRespondido);
    // Process open-ended responses
    params.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith("respuesta-"))
            .forEach(entry -> {
                Long preguntaId = Long.parseLong(entry.getKey().replace("respuesta-", ""));
                String respuesta = entry.getValue();
                Respuesta respuestaEntity = new Respuesta();
                respuestaEntity.setPregunta(repoPregunta.buscar(preguntaId));
                respuestaEntity.setRespuestaAbierta(respuesta);
                respuestaEntity.setCuestionarioRespondido(cuestionarioRespondido); // Associate with CuestionarioRespondido
                cuestionarioRespondido.agregarRespuesta(respuestaEntity);
                repoRespuesta.agregar(respuestaEntity);
            });
    return cuestionarioRespondido;
    }
}
