package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.distancias.CalculadorDistanciasTecnicoHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TecnicoController extends BaseController implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        verificarSesion(context, model);
        Usuario usuario = verificarTecnico(context, model);
        if (usuario != null) {
            model.put("title", "Cargar Tecnico");
            List<TipoDocumento> tipoDocumentos = List.of(TipoDocumento.values());
            model.put("tipoDocumentos", tipoDocumentos);
            context.render("tecnico/cargaTecnico.hbs", model);
        }
    }

    @Override
    public void save(Context context) throws ParseException {

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre(context.formParam("nombre"));
        tecnico.setApellido(context.formParam("apellido"));
        tecnico.setTipoDocumento(TipoDocumento.valueOf(context.formParam("tipoDocumento")));
        tecnico.setDni(Integer.valueOf(context.formParam("numeroDeDocumento")));
        tecnico.setCuil(Integer.valueOf(context.formParam("cuil")));
        tecnico.setAreaCobertura(Integer.valueOf(context.formParam("areaCobertura")));
        // TODO falta ingresar medio de contacto
        RepoTecnico.INSTANCE.agregar(tecnico);

    }

    @Override
    public void show(Context context) { // va a mostrar los incidentes del tecnico

        List<Incidente> incidentesTecnico = new ArrayList<>();
        Map<String, Object> model = new HashMap<>();
        Usuario tecnico = verificarTecnico(context, model);
        List<Heladera> heladerasCercanas = CalculadorDistanciasTecnicoHeladera.getInstance().healderasCercanasATecnico(tecnico);
        for (Heladera heladera : heladerasCercanas) {
            List<Incidente> incidentes = RepoIncidente.INSTANCE.getIncidentesPorHeladera(heladera.getId());
            for (Incidente incidente : incidentes) {
                if (incidente.getEstado() == false) { //osea no estan resueltos
                    incidentesTecnico.add(incidente);
                }
            }
            //incidentesTecnico.addAll(incidentes);
        }


        model.put("incidentes", incidentesTecnico);
        context.render("tecnico/incidentesTecnico.hbs", model);

    }

    public void perfil(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarSesion(context, model);
        if (usuario != null) {
            model.put("title", "Perfil Tecnico");
            Tecnico tecnico = RepoTecnico.INSTANCE.buscarPorUsuario(usuario.getId());
            model.put("tecnico", tecnico);
            context.render("tecnico/perfilTecnico.hbs", model);
        }
    }

    @Override
    public void create(Context context) {

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
