package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vulnerable.RegistroMenorACargo;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoMenorACargo;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoVulnerable;
import ar.edu.utn.frba.dds.server.Server;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RegistroVulnerableController extends BaseController implements ICrudViewsHandler {

    UserService userService = new UserService();

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarHumano(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        List<TipoDocumento> tipoDocumentos = List.of(TipoDocumento.values());
        List<Tarjeta> tarjetas = RepoTarjeta.INSTANCE.buscarTarjetasColaborador(colaborador.getId()).stream().filter(tarjeta -> tarjeta.getPersonaAsociada() == null).collect(Collectors.toList());
        model.put("tarjetas", tarjetas);
        model.put("tipoDocumentos", tipoDocumentos);
        model.put("title", "Registrar Persona Vulnerable");
        context.render("logs/registroVulnerable.hbs", model);

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
        Direccion direccion = new Direccion();
        direccion.setCalle(new Calle(context.formParam("calle")));
        direccion.setPiso(Integer.valueOf(context.formParam("piso")));
        direccion.setAltura(Integer.valueOf(context.formParam("altura")));
        RepoDireccion.INSTANCE.agregar(direccion);
        vulnerable.setDireccion(direccion);

        //menor a cargo
        menorACargo.setVulnerable(vulnerable);
        menorACargo.setTienePersonaACargo(Objects.equals(context.formParam("menoresACargo"), "si"));
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
        Server.registry.counter("tpdds.colaboraciones","status","registrarVulnerable").increment();
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
