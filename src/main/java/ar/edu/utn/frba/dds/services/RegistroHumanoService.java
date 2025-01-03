package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Opcion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import io.javalin.http.Context;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
                    Long preguntaId = Long.valueOf(entry.getKey().replace("respuesta-", ""));
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
                    Long preguntaId = Long.valueOf(entry.getKey().replace("opcion-", ""));
                    Long opcionId = Long.valueOf(entry.getValue());
                    Opcion opcion = repoOpcion.buscar(opcionId);
                    String respuesta = opcion.getTexto();
                    Respuesta respuestaEntity = new Respuesta();
                    Pregunta pregunta = repoPregunta.buscar(preguntaId);
                    setearCampo(pregunta, respuesta, colaborador);
                    respuestaEntity.setPregunta(pregunta);
                    respuestaEntity.setOpciones(Arrays.asList(opcion));
                    respuestaEntity.setCuestionarioRespondido(cuestionarioRespondido); // Associate with CuestionarioRespondido
                    cuestionarioRespondido.agregarRespuesta(respuestaEntity);
                    repoRespuesta.agregar(respuestaEntity);
                });

        params.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("fecha-"))
                .forEach(entry -> {
                    Long preguntaId = Long.valueOf(entry.getKey().replace("fecha-", ""));
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
                    }
                });

        String telefono = context.formParam("telefono");
        String correo = nuevoUsuario.getCorreoElectronico();
        List<Contacto> contacto = new ArrayList<>();

        List<String> mediosContacto = context.formParams("medios-contacto");
        // Verificar si se seleccionaron "WhatsApp" o "Telegram"
        boolean seleccionoWhatsApp = mediosContacto.contains("whatsapp");
        boolean seleccionoTelegram = mediosContacto.contains("telegram");


        if (telefono != null && !telefono.isEmpty()) {
            if (seleccionoWhatsApp) {
                Contacto contactoWhatsApp = new Contacto(TipoContacto.WPP, telefono);
                RepoContacto.INSTANCE.agregar(contactoWhatsApp);
                contacto.add(contactoWhatsApp);
            } // lo dejo comentado para no enviar todavía notificaciones por whatsapp y no gastar recursos
            if (seleccionoTelegram) {
                Contacto contactoTelegram = new Contacto(TipoContacto.TELEGRAM, telefono);
                RepoContacto.INSTANCE.agregar(contactoTelegram);
                contacto.add(contactoTelegram);
            }
        }
        if (correo != null && !correo.isEmpty()) {
            Contacto contactoCorreo = new Contacto(TipoContacto.MAIL, correo);
            RepoContacto.INSTANCE.agregar(contactoCorreo);
            contacto.add(contactoCorreo);
        }
        colaborador.setContacto(contacto);

        // return cuestionarioRespondido;

        return colaborador;
    }

    //caundo pasamos la respeusta al metodo ya deberia estar seteada al mismo tipo del campo que queremos completar de la clase que se este instanciando
    public <T> void setearCampo(Pregunta pregunta, T respuesta, Colaborador colaborador) {
        Class<Colaborador> claseColab = Colaborador.class;
        try {
            Field campo;
            try {//le agrego este trycatch para que no tire error cuando hace un registroHumano, espero no romper nada :s
                campo = claseColab.getDeclaredField(pregunta.getNombre());
            } catch (NoSuchFieldException e) {
                System.err.println("Campo no encontrado: " + pregunta.getNombre());
                return;
            }
            campo.setAccessible(true);
            Class<?> tipoCampo = campo.getType();

            Object valorConvertido;
            if (tipoCampo.equals(LocalDate.class) && respuesta instanceof String) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                valorConvertido = LocalDate.parse((String) respuesta, formatter);
            } else if (tipoCampo.equals(Integer.class)) {
                valorConvertido = Integer.valueOf((String) respuesta);
            } else if (tipoCampo.equals(Double.class)) {
                valorConvertido = Double.valueOf((String) respuesta);
            } else if (tipoCampo.isEnum()) {
                valorConvertido = Enum.valueOf((Class<Enum>) tipoCampo, (String) respuesta);
            } else if (tipoCampo.equals(String.class)){
                valorConvertido =(String) respuesta;
            } else {
                //if(nombrePregunta.equals("rubro")){valorConvertido = RepoRubroColaborador.INSTANCE.buscar();}
                valorConvertido = RepoRubroColaborador.INSTANCE.buscarPorNombre((String) respuesta);
            }


            campo.set(colaborador, valorConvertido);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
