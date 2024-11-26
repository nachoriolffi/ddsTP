package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.dtos.ViandaDTO;
import ar.edu.utn.frba.dds.dtos.outputs.DistribucionViandaOutputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.MotivoDistribucion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.TipoSolicitud;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.services.DistribuirViandasService;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistribuirViandasController extends BaseController implements ICrudViewsHandler {

    DistribuirViandasService distribuirViandasService = new DistribuirViandasService();
    RepoRegistroSolicitud repoRegistroSolicitud = new RepoRegistroSolicitud();
    UserService userService = new UserService();
    Map<String, Object> model = new HashMap<>();

    @Override
    public void index(Context context) {

        Usuario usuario = verificarHumano(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        List<Heladera> heladeras = distribuirViandasService.buscarHeladeras();
        List<HeladeraOutputDTO> heladeraOutputDTOS = new ArrayList<>();
        for (Heladera heladera : heladeras) {
            HeladeraOutputDTO h = new HeladeraOutputDTO();
            h.setId(String.valueOf(heladera.getId()));
            h.setNombre(heladera.getNombre());
            h.setViandasRestantes(String.valueOf(heladera.getViandasDisponibles()));
            h.setCapacidad(heladera.getModelo().getCantidadMaximaDeViandas());
            h.setLugarDisponible(heladera.getModelo().getCantidadMaximaDeViandas() - heladera.getViandasDisponibles());
            heladeraOutputDTOS.add(h);
        }
        List<Vianda> viandas = RepoViandas.INSTANCE.buscarTodos();
        List<ViandaDTO> viandaDTOS = new ArrayList<>();
        for (Vianda vianda : viandas) {
            ViandaDTO dto = new ViandaDTO();
            dto.setId(String.valueOf(vianda.getId()));
            dto.setComida(vianda.getComida());
            dto.setHeladeraId(String.valueOf(vianda.getHeladera().getId()));
            viandaDTOS.add(dto);
        }
        List<DistribucionViandaOutputDTO> distribucionViandaOutputDTOS = distribuirViandasService.buscarDistribucionesVianda(usuario);
        model.put("heladeras", heladeraOutputDTOS);
        model.put("viandas", viandaDTOS);
        model.put("distribuciones", distribucionViandaOutputDTOS);
        model.put("title", "Distribuir Viandas");
        context.render("donaciones/distribuirViandas.hbs", model);
    }

    @Override
    public void save(Context context) throws ParseException {

        Usuario usuario = verificarHumano(context, model);
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        Long heladeraOrigenId = Long.valueOf(context.formParam("nombreHeladeraOrigen"));
        Long heladeraDestinoId = Long.valueOf(context.formParam("nombreHeladeraDestino"));

        Heladera heladeraOrigen = RepoHeladeras.INSTANCE.buscar(heladeraOrigenId);
        Heladera heladeraDestino = RepoHeladeras.INSTANCE.buscar(heladeraDestinoId);
        List<String> viandasSeleccionadas = context.formParams("viandas[]");


        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setHeladeraOrigen(heladeraOrigen);
        distribucionVianda.setHeladeraDestino(heladeraDestino);
        distribucionVianda.setMotivo(MotivoDistribucion.valueOf(context.formParam("motivo")));
        String cantidadViandas = context.formParam("cantidadViandas");
        distribucionVianda.setCantidadViandas(Integer.valueOf(cantidadViandas));
        distribucionVianda.setFechaDistribucion(Date.valueOf(context.formParam("fechaDistribucion")));
        distribucionVianda.setFechaColaboracion(new java.util.Date());
        for (String viandaId : viandasSeleccionadas) {
            Vianda vianda = RepoViandas.INSTANCE.buscar(Long.valueOf(viandaId));
            distribucionVianda.getViandasMovidas().add(vianda);
        }
        distribucionVianda.setTipoColaboracion(TipoColaboracion.REDISTRIBUCION_VIANDAS);
        distribucionVianda.setColaborador(colaborador);

        RepoDistribucionVianda.INSTANCE.agregar(distribucionVianda);

        // ahora debo realizar los registros de sol de apertura
        Tarjeta tarjetaColaborador = new Tarjeta();
        List<Tarjeta> tarjetas = RepoTarjeta.INSTANCE.buscarTodos().stream().filter(tarjeta -> tarjeta.getColaboradorAsociado() != null).toList();
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getColaboradorAsociado().getId().equals(colaborador.getId())) {
                tarjetaColaborador = tarjeta;
            }
        }

        RegistroSolicitud registroSolAperturaOrigen = new RegistroSolicitud();
        registroSolAperturaOrigen.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        registroSolAperturaOrigen.setTarjeta(tarjetaColaborador);
        registroSolAperturaOrigen.setHeladeraAIr(heladeraOrigen);
        registroSolAperturaOrigen.setRetiroVianda(true);
        registroSolAperturaOrigen.setFechaSolicitud(new java.util.Date());
        registroSolAperturaOrigen.setCantidadViandas(distribucionVianda.getViandasMovidas());
        repoRegistroSolicitud.agregar(registroSolAperturaOrigen);
        RegistroSolicitud registroSolAperturaDestino = new RegistroSolicitud();
        registroSolAperturaDestino.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        registroSolAperturaDestino.setTarjeta(tarjetaColaborador);
        registroSolAperturaDestino.setHeladeraAIr(heladeraDestino);
        registroSolAperturaDestino.setRetiroVianda(false);
        registroSolAperturaDestino.setFechaSolicitud(new java.util.Date());
        registroSolAperturaDestino.setCantidadViandas(distribucionVianda.getViandasMovidas());
        repoRegistroSolicitud.agregar(registroSolAperturaDestino);
        colaborador.agregarColaboracionRealizada(distribucionVianda);
        RepoColaborador.INSTANCE.modificar(colaborador);
        context.redirect("/distribuirViandas");
    }

    public void obtenerViandasOrigen(Context context) {
        long heladeraId = Long.parseLong(context.queryParam("heladeraId"));
        List<Vianda> viandasHeladeraOrigen = distribuirViandasService.buscarViandas(heladeraId);
        context.json(viandasHeladeraOrigen);  // Retorna las viandas como respuesta JSON
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

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

}
