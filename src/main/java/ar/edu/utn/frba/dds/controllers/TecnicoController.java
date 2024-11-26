package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.factory.MedioComunicacionFactory;
import ar.edu.utn.frba.dds.models.entities.distancias.CalculadorDistancias;
import ar.edu.utn.frba.dds.models.entities.distancias.CalculadorDistanciasTecnicoHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoContacto;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import com.sun.mail.util.MailLogger;
import io.javalin.http.Context;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TecnicoController extends BaseController implements ICrudViewsHandler {

    UserService userService = new UserService();

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarAdmin(context,model);
        if (usuario != null) {
            model.put("title", "Cargar Tecnico");
            List<TipoDocumento> tipoDocumentos = List.of(TipoDocumento.values());
            List<TipoContacto> tipoContactos = List.of(TipoContacto.values());//le paso los medio de contacto y con esto puedo hacer algun tipo de facoptry despues o algho asi
            UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
            model.put("usuario", usuarioDTO);
            model.put("tipoContactos", tipoContactos);
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
        Long cuil = Long.valueOf(context.formParam("cuil"));
        tecnico.setCuil(cuil.intValue());
        tecnico.setAreaCobertura(Integer.valueOf(context.formParam("areaCobertura")));

        List<String> contactos = context.formParams("contactos");

        String telefono = context.formParam("telefono");
        String mail = context.formParam("correo");

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setApellido(context.formParam("apellido"));
        nuevoUsuario.setCorreoElectronico(mail);
        nuevoUsuario.setNombre(context.formParam("nombre"));
        nuevoUsuario.setRol(TipoRol.TECNICO);
        nuevoUsuario.setContrasenia(tecnico.getNombre()+"_"+ tecnico.getCuil());
        RepoUsuario.INSTANCE.agregar(nuevoUsuario);

        tecnico.setUsuario(nuevoUsuario);

        for (String contacto : contactos) {

            tecnico.agregarMedioDeComunicacion(MedioComunicacionFactory.createMedioDeComunicacion(contacto));

            //perdon mama por esto pero no me queda otra porq no voy a hacer un cambio por este enum no van a agregar un nuevo medio de contacto en la entrega

            if (contacto.equals("WPP") && telefono != null) {
                Contacto contactoTelefono = new Contacto(TipoContacto.WPP, telefono);
                RepoContacto.INSTANCE.agregar(contactoTelefono);
                tecnico.agregarContacto(contactoTelefono);
            }
            if (contacto.equals("MAIL") && mail != null) {
                Contacto contactoMail = new Contacto(TipoContacto.MAIL, mail);
                RepoContacto.INSTANCE.agregar(contactoMail);
                tecnico.agregarContacto(contactoMail);
            }
            if (contacto.equals("TELEGRAM") && telefono != null) {
                Contacto contactoTelefono = new Contacto(TipoContacto.TELEGRAM, telefono);
                RepoContacto.INSTANCE.agregar(contactoTelefono);
                tecnico.agregarContacto(contactoTelefono);
            }

            RepoTecnico.INSTANCE.agregar(tecnico);

        }
        context.redirect("/cargaTecnico");
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
