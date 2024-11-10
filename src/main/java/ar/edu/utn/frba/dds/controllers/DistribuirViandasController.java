package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.outputs.DistribucionViandaOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.MotivoDistribucion;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDistribucionVianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.services.DistribuirViandasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;

public class DistribuirViandasController extends BaseController implements ICrudViewsHandler {

    DistribuirViandasService distribuirViandasService = new DistribuirViandasService();

    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarHumano(context, model);
        List<Heladera> heladeras = distribuirViandasService.buscarHeladeras();
        List<DistribucionViandaOutputDTO> distribucionViandaOutputDTOS = distribuirViandasService.buscarDistribucionesVianda(usuario);
        model.put("heladeras", heladeras);
        model.put("distribuciones", distribucionViandaOutputDTOS);
        model.put("title", "Distribuir Viandas");
        context.render("donaciones/distribuirViandas.hbs", model);
    }

    @Override
    public void save(Context context) throws ParseException {

        Long heladeraOrigenId = Long.valueOf(context.formParam("nombreHeladeraOrigen"));
        Long heladeraDestinoId = Long.valueOf(context.formParam("nombreHeladeraDestino"));

        Heladera heladeraOrigen = RepoHeladeras.INSTANCE.buscar(heladeraOrigenId);
        Heladera heladeraDestino = RepoHeladeras.INSTANCE.buscar(heladeraDestinoId);

        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setHeladeraOrigen(heladeraOrigen);
        distribucionVianda.setHeladeraDestino(heladeraDestino);

        distribucionVianda.setMotivo(MotivoDistribucion.valueOf(context.formParam("motivo")));
        distribucionVianda.setCantidadViandas(Integer.valueOf(context.formParam("cantidad-viandas")));
        distribucionVianda.setFechaDistribucion(Date.valueOf(context.formParam("fecha-distribucion")));//probar
        distribucionVianda.setFechaColaboracion(new java.util.Date());

        RepoDistribucionVianda.INSTANCE.agregar(distribucionVianda);

        Long usuarioID = context.sessionAttribute("usuario_id");
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuarioID);
        colaborador.agregarColaboracionRealizada(distribucionVianda);
        RepoColaborador.INSTANCE.modificar(colaborador);

        context.redirect("/distribuirViandas");
    }

    public void obtenerViandasOrigen(Context context){
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
