package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Opcion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionarioRespondido;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOpcion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoPregunta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRespuesta;
import io.javalin.http.Context;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroHumanoService {

    RepoPregunta repoPregunta = RepoPregunta.INSTANCE;
    RepoRespuesta repoRespuesta = RepoRespuesta.INSTANCE;
    RepoOpcion repoOpcion = RepoOpcion.INSTANCE;
    public Colaborador processAndSaveResponses(Context context) {
        Usuario nuevoUsuario = context.sessionAttribute("nuevoUsuario");
        Colaborador colaborador = new Colaborador();
        colaborador.setUsuario(nuevoUsuario);

        Map<String, String> params = context.formParamMap().entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(0)));

    CuestionarioRespondido cuestionarioRespondido = new CuestionarioRespondido();
    RepoCuestionarioRespondido.INSTANCE.agregar(cuestionarioRespondido);

    params.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith("respuesta-"))
            .forEach(entry -> {
                Long preguntaId = Long.parseLong(entry.getKey().replace("respuesta-", ""));
                String respuesta = entry.getValue();
                Respuesta respuestaEntity = new Respuesta();
                Pregunta pregunta = repoPregunta.buscar(preguntaId);
                setearCampo(pregunta, respuesta, colaborador);
                respuestaEntity.setPregunta(pregunta);
                respuestaEntity.setRespuestaAbierta(respuesta);
                respuestaEntity.setCuestionarioRespondido(cuestionarioRespondido); // Associate with CuestionarioRespondido
                cuestionarioRespondido.agregarRespuesta(respuestaEntity);
                repoRespuesta.agregar(respuestaEntity);
            });

        params.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("opcion-"))
                .forEach(entry -> {
                    Long preguntaId = Long.parseLong(entry.getKey().replace("opcion-", ""));
                    Long opcionId = Long.parseLong(entry.getValue());
                    String respuesta = entry.getValue();
                    Opcion opcion = repoOpcion.buscar(opcionId);
                    Respuesta respuestaEntity = new Respuesta();
                    Pregunta pregunta = repoPregunta.buscar(preguntaId);
                    respuestaEntity.setPregunta(pregunta);
                    respuestaEntity.setOpciones(Arrays.asList(opcion));
                    respuestaEntity.setCuestionarioRespondido(cuestionarioRespondido); // Associate with CuestionarioRespondido
                    cuestionarioRespondido.agregarRespuesta(respuestaEntity);
                    repoRespuesta.agregar(respuestaEntity);
                });

        params.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("fecha-"))
                .forEach(entry -> {
                    Long preguntaId = Long.parseLong(entry.getKey().replace("fecha-", ""));
                    String fechaString = entry.getValue();
                    try {
                        Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
                        Respuesta respuestaEntity = new Respuesta();
                        Pregunta pregunta = repoPregunta.buscar(preguntaId);
                        setearCampo(pregunta, fechaString, colaborador);
                        respuestaEntity.setPregunta(pregunta);
                        respuestaEntity.setFecha(fecha);
                        respuestaEntity.setCuestionarioRespondido(cuestionarioRespondido);
                        cuestionarioRespondido.agregarRespuesta(respuestaEntity);
                        repoRespuesta.agregar(respuestaEntity);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });

       // return cuestionarioRespondido;

        return colaborador;
    }

    //caundo pasamos la respeusta al metodo ya deberia estar seteada al mismo tipo del campo que queremos completar de la clase que se este instanciando
    public <T> void setearCampo(Pregunta pregunta, T respuesta, Colaborador colaborador) {
        Class<Colaborador> claseColab = Colaborador.class;
        try {
            Field campo = claseColab.getDeclaredField(pregunta.getNombre());
            campo.setAccessible(true);
            if (campo.getType().equals(LocalDate.class) && respuesta instanceof String) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fecha = LocalDate.parse((String) respuesta, formatter);
                campo.set(colaborador, fecha);
            } else {
                campo.set(colaborador, respuesta);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
