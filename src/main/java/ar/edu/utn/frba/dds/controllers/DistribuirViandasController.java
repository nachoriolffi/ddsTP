package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.outputs.DistribucionViandaOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.MotivoDistribucion;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDistribucionVianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;

public class DistribuirViandasController extends BaseController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarHumano(context, model);
        List<Heladera> heladeras = RepoHeladeras.INSTANCE.buscarTodos();
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        List<DistribucionVianda> distribuciones = colaborador.getColaboracionesRealizadas().stream()
                .filter(c -> c instanceof DistribucionVianda)
                .map(c -> (DistribucionVianda) c)
                .toList();

        List<DistribucionViandaOutputDTO> distribucionViandaOutputDTOS = new ArrayList<>();
        for (DistribucionVianda distribucion : distribuciones) {
            DistribucionViandaOutputDTO distribucionViandaOutputDTO = new DistribucionViandaOutputDTO();
            distribucionViandaOutputDTO.setViandasMovidas(String.valueOf(distribucion.getCantidadViandas()));
            distribucionViandaOutputDTO.setMotivo(distribucion.getMotivo().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            distribucionViandaOutputDTO.setFechaDistribucion(sdf.format(distribucion.getFechaDistribucion()));
            distribucionViandaOutputDTO.setHeladeraOrigen(distribucion.getHeladeraOrigen().getNombre());
            distribucionViandaOutputDTO.setHeladeraDestino(distribucion.getHeladeraDestino().getNombre());
            distribucionViandaOutputDTOS.add(distribucionViandaOutputDTO);

        }
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

        //Lo siguiente se haría cuando el colaborador asista personalmente a realizar la distribución:
        //Habría que quitar las viandas de la heladera A segun la cantidad y agregarlas a la heladera B
        //Despues hay que hacer un update o modificar
        context.redirect("/distribuirViandas");
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
