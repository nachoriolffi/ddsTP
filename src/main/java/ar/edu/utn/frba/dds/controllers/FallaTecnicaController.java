package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente.FALLA;

public class FallaTecnicaController extends BaseController implements ICrudViewsHandler {

    private static final String UPLOAD_DIR = "src/main/resources/public/imagenes/";
    private final RepoIncidente repositorioIncidentes = RepoIncidente.INSTANCE;
    private final UserService userService = new UserService();
    public static Date convertToDate(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
        return Date.valueOf(localDateTime.toLocalDate());
    }

    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarRol(context, model, TipoRol.COLABORADOR_HUMANO);
        System.out.println(usuario.getNombre());
        System.out.println(usuario.getApellido());
        System.out.println(usuario.getId());
        System.out.println(usuario.getCorreoElectronico());
        model.put("title", "Reportar Falla Tecnica");
        List<Heladera> heladeras = RepoHeladeras.INSTANCE.buscarTodos().stream().filter(heladera -> heladera.getEstaActiva()).toList();
        model.put("heladeras", heladeras);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        context.render("FallaTecnica.hbs", model);
    }

    @Override
    public void save(Context context) {
        Incidente nuevoIncidente = new Incidente();
        String id = context.formParam("heladeraAveriada");
        Long idLong = Long.valueOf(id);
        Heladera heladera = RepoHeladeras.INSTANCE.buscar(idLong);
        nuevoIncidente.setHeladera(heladera);
        nuevoIncidente.setTipoIncidente(FALLA);
        nuevoIncidente.setDescripcion(context.formParam("descripcionFalla"));
        String fecha = context.formParam("diaYHora");

        // Handle file upload
        UploadedFile uploadedFile = context.uploadedFile("pathFoto");
        if (uploadedFile != null) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.filename();
            String filePath = UPLOAD_DIR + fileName;
            try {
                Files.createDirectories(Paths.get(UPLOAD_DIR));
                Files.write(Paths.get(filePath), uploadedFile.content().readAllBytes());
                nuevoIncidente.setPathFoto(filePath);
            } catch (IOException e) {
                context.status(500).result("File upload failed: " + e.getMessage());
                return;
            }
        }

        nuevoIncidente.setFecha(Date.valueOf(convertToDate(fecha).toLocalDate()));
        nuevoIncidente.setPathFoto(context.formParam("fotoFalla"));
        heladera.setEstaActiva(false);

        nuevoIncidente.setEstado(false);
        this.repositorioIncidentes.agregar(nuevoIncidente);
        RepoHeladeras.INSTANCE.modificar(heladera);
        context.redirect("fallaTecnica");
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

    @Override
    public void create(Context context) {

    }


}
