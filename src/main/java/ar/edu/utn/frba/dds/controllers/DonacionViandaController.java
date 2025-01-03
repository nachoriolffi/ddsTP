package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.dtos.outputs.DonacionViandaOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionVianda;
import ar.edu.utn.frba.dds.models.entities.generadorCodigo.GeneradorDeCodigo;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.TipoSolicitud;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.server.Server;
import ar.edu.utn.frba.dds.services.HeladeraService;
import ar.edu.utn.frba.dds.services.RegistroSolicitudService;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion.DONACION_VIANDAS;

public class DonacionViandaController extends BaseController implements ICrudViewsHandler {


    RepoDonacionVianda repoDonacionVianda = RepoDonacionVianda.INSTANCE;
    RepoViandas repoViandas = RepoViandas.INSTANCE;
    RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
    RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
    UserService userService = new UserService();
    HeladeraService heladeraService = new HeladeraService();

    RegistroSolicitudService registroSolicitudService = new RegistroSolicitudService();


    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();

        Usuario usuario = verificarHumano(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());

        List<DonacionVianda> donacionesViandas = colaborador.getColaboracionesRealizadas().stream()
                .filter(c -> c instanceof DonacionVianda)
                .map(c -> (DonacionVianda) c)
                .toList();
        List<Heladera> heladeras = heladeraService.buscarHeladeras();

        List<Vianda> viandas = donacionesViandas.stream()
                .map(DonacionVianda::getVianda)
                .filter(Objects::nonNull)
                .toList();
        List<DonacionViandaOutputDTO> donacionesViandasDTO = new ArrayList<>();
        for (Vianda vianda : viandas) {
            DonacionViandaOutputDTO donacionViandaOutputDTO = getDonacionViandaOutputDTO(vianda);
            donacionesViandasDTO.add(donacionViandaOutputDTO);
        }
        model.put("heladeras", heladeras);
        model.put("title", "Donar Vianda");
        model.put("viandas", donacionesViandasDTO);

        context.render("donaciones/donacionViandas.hbs", model);
    }

    private DonacionViandaOutputDTO getDonacionViandaOutputDTO(Vianda vianda) {
        DonacionViandaOutputDTO donacionViandaOutputDTO = new DonacionViandaOutputDTO();

        // Comprobar si vianda.getCalorias() es null, en cuyo caso asignar ""
        donacionViandaOutputDTO.setCalorias(vianda.getCalorias() != null ? String.valueOf(vianda.getCalorias()) : "");

        // Comprobar si vianda.getComida() es null, en cuyo caso asignar ""
        donacionViandaOutputDTO.setComida(vianda.getComida() != null ? vianda.getComida() : "");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Comprobar si vianda.getFechaDonacion() es null, en cuyo caso asignar ""
        Date fechaDonacion = vianda.getFechaDonacion();
        donacionViandaOutputDTO.setFechaDonacion(fechaDonacion != null ? sdf.format(fechaDonacion) : "");

        // Comprobar si vianda.getHeladera() es null o vianda.getHeladera().getNombre() es null, en cuyo caso asignar ""
        donacionViandaOutputDTO.setHeladera(vianda.getHeladera() != null && vianda.getHeladera().getNombre() != null ? vianda.getHeladera().getNombre() : "");

        // Comprobar si vianda.getFueEntregada() es null, en cuyo caso asignar ""
        donacionViandaOutputDTO.setFueEntregada(vianda.getFueEntregada() != null ? String.valueOf(vianda.getFueEntregada() ? "Si" : "No") : "");

        // Comprobar si vianda.getFechaCaducidad() es null, en cuyo caso asignar ""
        donacionViandaOutputDTO.setFechaCaducidad(vianda.getFechaCaducidad() != null ? sdf.format(vianda.getFechaCaducidad()) : "");

        // Comprobar si vianda.getPeso() es null, en cuyo caso asignar ""
        donacionViandaOutputDTO.setPesoEnGramos(vianda.getPeso() != null ? vianda.getPeso().toString() : "");

        return donacionViandaOutputDTO;
    }


    @Override
    public void show(Context context) {
    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws Exception {
        try {
            System.out.println("Inicio del método save");

            Long usuarioID = context.sessionAttribute("usuario_id");
            System.out.println("Usuario ID obtenido: " + usuarioID);

            DonacionVianda donacionVianda = new DonacionVianda();
            Vianda nuevaVianda = new Vianda();

            System.out.println("Comenzando a setear los datos de la nueva vianda");
            nuevaVianda.setComida(context.formParam("comida"));
            System.out.println("Comida: " + nuevaVianda.getComida());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            nuevaVianda.setFechaCaducidad(dateFormat.parse(context.formParam("fechaCaducidad")));
            System.out.println("Fecha de caducidad: " + nuevaVianda.getFechaCaducidad());

            nuevaVianda.setCalorias(Double.valueOf(Objects.requireNonNull(context.formParam("calorias"))));
            System.out.println("Calorías: " + nuevaVianda.getCalorias());

            nuevaVianda.setPeso(Double.valueOf(Objects.requireNonNull(context.formParam("peso"))));
            System.out.println("Peso: " + nuevaVianda.getPeso());

            nuevaVianda.setFechaDonacion(new Date());
            System.out.println("Fecha de donación: " + nuevaVianda.getFechaDonacion());

            System.out.println("Buscando heladera...");
            Heladera heladera = repoHeladeras.buscar(Long.valueOf(context.formParam("nombreHeladera")));
            System.out.println("Heladera encontrada: " + heladera);

            System.out.println("Buscando colaborador...");
            Colaborador colaborador = repoColaborador.buscarPorIdUsuario(usuarioID);
            System.out.println("Colaborador encontrado: " + colaborador);

            nuevaVianda.setColaborador(colaborador);
            nuevaVianda.setFueEntregada(false);
            nuevaVianda.setHeladera(heladera);

            System.out.println("Agregando nueva vianda al repositorio...");
            repoViandas.agregar(nuevaVianda);
            System.out.println("Vianda agregada: " + nuevaVianda);

            donacionVianda.setVianda(nuevaVianda);
            donacionVianda.setFechaColaboracion(new Date());
            donacionVianda.setTipoColaboracion(DONACION_VIANDAS);

            System.out.println("Agregando donación de vianda al repositorio...");
            repoDonacionVianda.agregar(donacionVianda);
            System.out.println("Donación de vianda agregada: " + donacionVianda);

            colaborador.agregarColaboracionRealizada(donacionVianda);
            System.out.println("Actualizando colaborador con la nueva colaboración...");
            RepoColaborador.INSTANCE.modificar(colaborador);
            System.out.println("Colaborador actualizado.");

            System.out.println("Buscando tarjeta...");
            Tarjeta tarjeta = null;
            List<Tarjeta> tarjetas = RepoTarjeta.INSTANCE.buscarTarjetasColaborador(colaborador.getId());
            for (Tarjeta t : tarjetas) {
                if (t.getColaboradorAsignador() == null && t.getPersonaAsociada() == null) {
                    tarjeta = t;
                    break;
                }
            }

            if (tarjeta == null) {
                System.out.println("No se encontró una tarjeta disponible, creando una nueva...");
                tarjeta = new Tarjeta();
                tarjeta.setCodigo(GeneradorDeCodigo.getInstance().generarCodigoUnico());
                tarjeta.setColaboradorAsociado(colaborador);
                RepoTarjeta.INSTANCE.agregar(tarjeta);
                System.out.println("Nueva tarjeta creada: " + tarjeta);
            }

            System.out.println("Registrando solicitud...");
            registroSolicitudService.registrarSolicitud(TipoSolicitud.DONACION_VIANDA, tarjeta, heladera, nuevaVianda);
            System.out.println("Solicitud registrada.");

            System.out.println("Redirigiendo al usuario...");
            context.redirect("/donarViandas");

            // Uncomment the counter increment if needed.
            Server.registry.counter("tpdds.colaboraciones", "status", "donacionesVianda").increment();
        } catch (ParseException e) {
            System.out.println("Error de formato de fecha: " + e.getMessage());
            context.status(400).result("Formato de fecha inválido.");
        } catch (Exception e) {
            System.out.println("Error interno del servidor: " + e.getMessage());
            context.status(500).result("Error interno del servidor: " + e.getMessage());
        }
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
