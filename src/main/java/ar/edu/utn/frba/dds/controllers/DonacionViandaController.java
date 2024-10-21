package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.outputs.DonacionViandaOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionVianda;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionVianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoViandas;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion.DONACION_VIANDAS;

public class DonacionViandaController extends BaseController implements ICrudViewsHandler {


    RepoDonacionVianda repoDonacionVianda = RepoDonacionVianda.INSTANCE;
    RepoViandas repoViandas = RepoViandas.INSTANCE;
    RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
    RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;

    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();

        Usuario usuario = verificarHumano(context, model);
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());

        List<DonacionVianda> donacionesViandas = colaborador.getColaboracionesRealizadas().stream()
                .filter(c -> c instanceof DonacionVianda)
                .map(c -> (DonacionVianda) c)
                .toList();
        List<Heladera> heladeras = repoHeladeras.buscarTodos();

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
    public void save(Context context) throws ParseException {

        Long usuarioID = context.sessionAttribute("usuario_id");

        DonacionVianda donacionVianda = new DonacionVianda();
        Vianda nuevaVianda = new Vianda();

        nuevaVianda.setComida(context.formParam("comida"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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

        context.redirect("/donarViandas");

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
