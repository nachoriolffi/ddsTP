package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TecnicoController  extends BaseController implements ICrudViewsHandler {


    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        verificarSesion(context, model);
        Usuario usuario = usuarioLogueado(context);

        if(usuario!=null){
            model.put("title", "Tecnico");
            List<TipoDocumento> tipoDocumentos = List.of(TipoDocumento.values());
            model.put("tipoDocumentos", tipoDocumentos);
            context.render("tecnico/cargaTecnico.hbs", model);
        }
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

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

        RepoTecnico.INSTANCE.agregar(tecnico);

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
