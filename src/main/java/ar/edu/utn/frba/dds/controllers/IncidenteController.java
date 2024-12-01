package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.dtos.outputs.IncidenteOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.*;

public class IncidenteController extends BaseController implements ICrudViewsHandler {

    UserService userService = new UserService();

    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarTecnico(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        Incidente incidente = RepoIncidente.INSTANCE.buscar(Long.parseLong(Objects.requireNonNull(context.pathParam("id"))));

        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
    }

    public void fallasTecnicas(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarAdminOJuridico(context, model);

        List<Incidente> incidentes = RepoIncidente.INSTANCE.buscarTodos();
        List<IncidenteOutputDTO> incidenteOutputDTOList = new ArrayList<>();
        for (Incidente incidente : incidentes) {
            IncidenteOutputDTO incidenteOutputDTO = new IncidenteOutputDTO();
            incidenteOutputDTO.setTipoIncidente(incidente.getTipoIncidente().ordinal());
            incidenteOutputDTO.setHeladera(incidente.getHeladera().getNombre());
            incidenteOutputDTO.setEstado(String.valueOf(incidente.getEstado()));
            incidenteOutputDTO.setFecha(incidente.getFecha().toString());
        }
        model.put("incidentes", incidentes);
        model.put("usuario", usuario);
        context.render("incidentes/verFallas.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

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
