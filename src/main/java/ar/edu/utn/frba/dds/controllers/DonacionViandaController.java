package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.dtos.outputs.DonacionViandaOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionVianda;
import ar.edu.utn.frba.dds.models.entities.generadorCodigo.GeneradorDeCodigo;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.TipoSolicitud;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
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
        donacionViandaOutputDTO.setCalorias(String.valueOf(vianda.getCalorias()));
        donacionViandaOutputDTO.setComida(vianda.getComida());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDonacion = vianda.getFechaDonacion();
        donacionViandaOutputDTO.setFechaDonacion(sdf.format(fechaDonacion));
        donacionViandaOutputDTO.setHeladera(vianda.getHeladera().getNombre());
        donacionViandaOutputDTO.setFueEntregada(String.valueOf(vianda.getFueEntregada() ? "Si" : "No"));
        donacionViandaOutputDTO.setFechaCaducidad(sdf.format((vianda.getFechaCaducidad())));
        donacionViandaOutputDTO.setPesoEnGramos(vianda.getPeso().toString());
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
            Long usuarioID = context.sessionAttribute("usuario_id");

            DonacionVianda donacionVianda = new DonacionVianda();
            Vianda nuevaVianda = new Vianda();

            nuevaVianda.setComida(context.formParam("comida"));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            nuevaVianda.setFechaCaducidad(formatter.parse(context.formParam("fechaCaducidad")));
            nuevaVianda.setCalorias(Double.valueOf(Objects.requireNonNull(context.formParam("calorias"))));
            nuevaVianda.setPeso(Double.valueOf(Objects.requireNonNull(context.formParam("peso"))));
            nuevaVianda.setFechaDonacion(new Date());

            Heladera heladera = repoHeladeras.buscar(Long.parseLong(context.formParam("nombreHeladera")));

            Colaborador colaborador = repoColaborador.buscarPorIdUsuario(usuarioID);

            nuevaVianda.setColaborador(colaborador);
            nuevaVianda.setFueEntregada(false);
            nuevaVianda.setHeladera(heladera);
            repoViandas.agregar(nuevaVianda);

            donacionVianda.setVianda(nuevaVianda);
            donacionVianda.setFechaColaboracion(new Date());
            donacionVianda.setTipoColaboracion(DONACION_VIANDAS);
            repoDonacionVianda.agregar(donacionVianda);
            colaborador.agregarColaboracionRealizada(donacionVianda);
            RepoColaborador.INSTANCE.modificar(colaborador);

            RegistroSolicitud solicutud = new RegistroSolicitud();
            Tarjeta tarjeta = null;
            List<Tarjeta> tarjetas = RepoTarjeta.INSTANCE.buscarTarjetasColaborador(colaborador.getId());
            for (Tarjeta t : tarjetas) {
                if (t.getColaboradorAsignador() == null && t.getPersonaAsociada() == null) {
                    tarjeta = t;
                    break;
                }
            }
            if (tarjeta == null) {
                tarjeta = new Tarjeta();
                tarjeta.setCodigo(GeneradorDeCodigo.getInstance().generarCodigoUnico());
                tarjeta.setColaboradorAsociado(colaborador);
                RepoTarjeta.INSTANCE.agregar(tarjeta);
            }
            registroSolicitudService.registrarSolicitud(TipoSolicitud.DONACION_VIANDA, tarjeta, heladera, nuevaVianda);

            context.redirect("/donarViandas");
            //Server.registry.counter("tpdds.colaboraciones","status","donacionesVianda").increment();
        } catch (ParseException e) {
            context.status(400).result("Formato de fecha inválido.");
        } catch (Exception e) {
            context.status(500).result("Error interno del servidor: " + e.getMessage());
            e.printStackTrace();
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
