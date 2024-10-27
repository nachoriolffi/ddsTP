package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vulnerable.RegistroMenorACargo;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoMenorACargo;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoVulnerable;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import io.javalin.http.Context;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RegistroVulnerableController extends BaseController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarHumano(context, model);
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        if (usuario != null) {
            List<TipoDocumento> tipoDocumentos = List.of(TipoDocumento.values());
            List<Tarjeta> tarjetas = RepoTarjeta.INSTANCE.buscarTarjetasColaborador(colaborador.getId());
            model.put("tarjetas", tarjetas);
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
        RegistroMenorACargo menorACargo = new RegistroMenorACargo();
        vulnerable.setNombre(context.formParam("nombre"));
        vulnerable.setApellido(context.formParam("apellido"));
        vulnerable.setTipoDocumento(TipoDocumento.valueOf(context.formParam("tipoDocumento")));
        vulnerable.setNumeroDocumento(Integer.valueOf(Objects.requireNonNull(context.formParam("numeroDeDocumento"))));
        vulnerable.setFechaDeNacimiento(java.sql.Date.valueOf(Objects.requireNonNull(context.formParam("fechaNacimiento"))));
        vulnerable.setFechaDeRegistro(new java.sql.Date(System.currentTimeMillis()));
        vulnerable.setSituacionDeCalle(Boolean.valueOf(context.formParam("situacionDeCalle")));

        //menor a cargo
        menorACargo.setVulnerable(vulnerable);
        menorACargo.setTienePersonaACargo(Boolean.valueOf(context.formParam("menoresACargo")));
        menorACargo.setCantidadDePersonasACargo(Integer.valueOf(Objects.requireNonNull(context.formParam("cantidadMenores"))));
        menorACargo.setFechaRegistro(new java.sql.Date(System.currentTimeMillis()));
        vulnerable.getMenoresACargo().add(menorACargo);

        //tarjeta
        Tarjeta tarjeta = RepoTarjeta.INSTANCE.buscar(Long.valueOf(Objects.requireNonNull(context.formParam("tarjeta"))));
        tarjeta.setPersonaAsociada(vulnerable);

        //carga repos
        RepoMenorACargo.INSTANCE.agregar(menorACargo);
        RepoVulnerable.INSTANCE.agregar(vulnerable);
        RepoTarjeta.INSTANCE.modificar(tarjeta);
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
