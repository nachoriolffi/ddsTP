package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.dtos.outputs.IncidenteOutputDTO;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.AdapterCorreo;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.CorreoElectronico;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.distancias.CalculadorDistanciasTecnicoHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import io.javalin.http.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TecnicoController extends BaseController implements ICrudViewsHandler {

    UserService userService = new UserService();

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarAdmin(context, model);
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
        Coordenada coordenada = new Coordenada();
        coordenada.setLatitud(-34.603722);
        coordenada.setLongitud(-58.381592);
        RepoCoordenada.INSTANCE.agregar(coordenada);
        tecnico.setCoordenada(coordenada);
        tecnico.setNombre(context.formParam("nombre"));
        tecnico.setApellido(context.formParam("apellido"));
        tecnico.setTipoDocumento(TipoDocumento.valueOf(context.formParam("tipoDocumento")));
        tecnico.setDni(Integer.valueOf(Objects.requireNonNull(context.formParam("numeroDeDocumento"))));
        Long cuil = Long.valueOf(Objects.requireNonNull(context.formParam("cuil")));
        tecnico.setCuil(cuil.intValue());
        RepoCoordenada.INSTANCE.agregar(coordenada);
        tecnico.setCoordenada(coordenada);
        tecnico.setAreaCobertura(Integer.valueOf(context.formParam("areaCobertura")));

        List<String> contactos = context.formParams("contactos");

        String telefono = context.formParam("telefono");
        String mail = context.formParam("correo");

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setApellido(context.formParam("apellido"));
        nuevoUsuario.setCorreoElectronico(mail);
        nuevoUsuario.setNombre(context.formParam("nombre"));
        nuevoUsuario.setRol(TipoRol.TECNICO);
        nuevoUsuario.setContrasenia(tecnico.getNombre() + "_" + tecnico.getCuil());
        RepoUsuario.INSTANCE.agregar(nuevoUsuario);

        tecnico.setUsuario(nuevoUsuario);

        for (String contacto : contactos) {

            //tecnico.agregarMedioDeComunicacion(MedioComunicacionFactory.createMedioDeComunicacion(contacto));

            //perdon mama por esto pero no me queda otra porq no voy a hacer un cambio por este enum no van a agregar un nuevo medio de contacto en la entrega
            List<MedioDeComunicacion> mediosDeComunicacion = new ArrayList<>();

            if (contacto.equals("WPP") && telefono != null) {
                Contacto contactoTelefono = new Contacto(TipoContacto.WPP, telefono);
                RepoContacto.INSTANCE.agregar(contactoTelefono);
                tecnico.agregarContacto(contactoTelefono);
            }
            if (contacto.equals("MAIL") && mail != null) {
                Contacto contactoMail = new Contacto(TipoContacto.MAIL, mail);
                RepoContacto.INSTANCE.agregar(contactoMail);
                tecnico.agregarContacto(contactoMail);
                mediosDeComunicacion.add(new CorreoElectronico(new AdapterCorreo()));
            }
            if (contacto.equals("TELEGRAM") && telefono != null) {
                Contacto contactoTelefono = new Contacto(TipoContacto.TELEGRAM, telefono);
                RepoContacto.INSTANCE.agregar(contactoTelefono);
                tecnico.agregarContacto(contactoTelefono);

            }
            tecnico.setDisponible(true);
            tecnico.setMediosDeComunicacion(mediosDeComunicacion);
            RepoTecnico.INSTANCE.agregar(tecnico);

        }
        context.redirect("/cargaTecnico");
    }

    @Override
    public void show(Context context) { // va a mostrar los incidentes del tecnico
        List<IncidenteOutputDTO> incidentesTecnicoDTO = new ArrayList<>();
        List<Incidente> incidentesTecnico = new ArrayList<>();
        Map<String, Object> model = new HashMap<>();
        Usuario tecnico = verificarTecnico(context, model);
        List<Heladera> heladerasCercanas = CalculadorDistanciasTecnicoHeladera.getInstance().healderasCercanasATecnico(tecnico);
        for (Heladera heladera : heladerasCercanas) {
            List<Incidente> incidentes = RepoIncidente.INSTANCE.getIncidentesPorHeladera(heladera.getId());


            for (Incidente incidente : incidentes) {
                if (incidente.getEstado() == Boolean.FALSE) { // Solo los no resueltos
                    IncidenteOutputDTO dto = new IncidenteOutputDTO();

                    dto.setId(incidente.getId().intValue()); // Convertimos Long a Integer
                    dto.setTipoIncidente(incidente.getTipoIncidente() != null ? incidente.getTipoIncidente().ordinal() : null);
                    dto.setTipoAlerta(incidente.getTipoAlerta() != null ? incidente.getTipoAlerta().ordinal() : null);
                    dto.setDescripcion(incidente.getDescripcion() != null ? incidente.getDescripcion() : "");
                    dto.setPathFoto(incidente.getPathFoto() != null ? incidente.getPathFoto() : "");
                    dto.setEstado(incidente.getEstado() ? "Resuelto" : "No resuelto");
                    dto.setHeladera(incidente.getHeladera() != null ? incidente.getHeladera().getNombre() : "");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    dto.setFecha(incidente.getFecha() != null ? sdf.format(incidente.getFecha()) : "");

                    incidentesTecnicoDTO.add(dto);
                }
            }
        }

        model.put("incidentes", incidentesTecnicoDTO);
        context.render("tecnico/incidentesTecnico.hbs", model);

    }

    public void perfil(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarSesion(context, model);
        if (usuario != null) {
            model.put("title", "Perfil Tecnico");
            Tecnico tecnico = RepoTecnico.INSTANCE.buscarPorUsuario(usuario.getId());
            model.put("tecnico", tecnico);
            context.render("tecnico/PerfilTecnico.hbs", model);
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
