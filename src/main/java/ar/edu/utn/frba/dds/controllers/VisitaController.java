package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroVisita;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistrosVisita;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VisitaController extends BaseController implements ICrudViewsHandler {

    UserService userService = new UserService();

    @Override
    public void index(Context context) {
        //vamos a la visa para la carga de la visita necesitamos el id del incidente y el id del tecnico que lo tenemos en la sesion
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarSesion(context,model);

        Tecnico tecnico = RepoTecnico.INSTANCE.buscarPorUsuario(usuario.getId());
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        // ya conozco al tecnico ahora necesito el incidenteç
        String incidenteIdStr = context.queryParam("id");
        String id = context.formParam("id");

        Incidente incidente = RepoIncidente.INSTANCE.buscar(Long.parseLong( incidenteIdStr));

        model.put("incidente", incidente);

        context.render("tecnico/registroVisita.hbs", model);

    }

    @Override
    public void show(Context context) {

    }

    private static final String UPLOAD_DIR = "src/main/resources/public/imagenes/";

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarSesion(context, model);

        Tecnico tecnico = RepoTecnico.INSTANCE.buscarPorUsuario(usuario.getId());
        String incidenteIdStr = context.formParam("id");
        if (incidenteIdStr == null) {
            // Handle the error appropriately
            context.status(400).result("Incidente ID is required");
            return;
        }

        Long incidenteId = Long.parseLong(incidenteIdStr);
        Incidente incidente = RepoIncidente.INSTANCE.buscar(incidenteId);

        RegistroVisita registroVisita = new RegistroVisita();
        registroVisita.setTecnico(tecnico);
        registroVisita.setIncidenteASolucionar(incidente);
        registroVisita.setDescripcion(context.formParam("descripcion"));

        // Handle file upload
        UploadedFile uploadedFile = context.uploadedFile("pathFoto");
        if (uploadedFile != null) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.filename();
            String filePath = UPLOAD_DIR + fileName;
            try {
                Files.createDirectories(Paths.get(UPLOAD_DIR));
                Files.write(Paths.get(filePath), uploadedFile.content().readAllBytes());
                registroVisita.setPathFoto(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                context.status(500).result("File upload failed: " + e.getMessage());
                return;
            }
        }



        Boolean solucionado = "true".equals(context.formParam("problemaSolucionado"));
        registroVisita.setProblemaSolucionado(solucionado);
        registroVisita.setFechaVisita(new java.util.Date());

        if (solucionado) {
            Heladera heladera = incidente.getHeladera();
            heladera.setEstaActiva(true);
            incidente.setEstado(solucionado);
            RepoIncidente.INSTANCE.modificar(incidente);
            RepoHeladeras.INSTANCE.modificar( heladera  );
        }



        RepoRegistrosVisita.INSTANCE.agregar(registroVisita);

        context.redirect("/IncidentesTecnico");
    }
    // Other methods...

    @Override
    public void save(Context context) throws ParseException {

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
