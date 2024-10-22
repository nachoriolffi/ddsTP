package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoVulnerable;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RegistroVulnerableController extends BaseController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarHumano(context, model);
        if (usuario != null) {
            List<TipoDocumento> tipoDocumentos = List.of(TipoDocumento.values());
            model.put("tipoDocumentos", tipoDocumentos);
            model.put("title", "Registrar Persona Vulnerable");
            context.render("logs/registroVulnerable.hbs", model);
        } else {
            context.status(403);
        }

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

        Vulnerable vulnerable = new Vulnerable();
        vulnerable.setNombre(context.formParam("nombre"));
        vulnerable.setApellido(context.formParam("apellido"));
        vulnerable.setTipoDocumento(TipoDocumento.valueOf(context.formParam("tipoDocumento")));
        vulnerable.setNumeroDocumento(Integer.valueOf(Objects.requireNonNull(context.formParam("numeroDeDocumento"))));
        vulnerable.setFechaDeNacimiento(java.sql.Date.valueOf(Objects.requireNonNull(context.formParam("fechaNacimiento"))));
        vulnerable.setFechaDeRegistro(new java.sql.Date(System.currentTimeMillis()));
        vulnerable.setSituacionDeCalle(Boolean.valueOf(context.formParam("situacionDeCalle")));
        RepoVulnerable.INSTANCE.agregar(vulnerable);
        context.redirect("/registroVulnerable");
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
