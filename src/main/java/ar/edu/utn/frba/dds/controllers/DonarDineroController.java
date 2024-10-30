package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.outputs.DonacionDineroOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionDinero;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DonarDineroController extends BaseController implements ICrudViewsHandler {

    RepoDonacionDinero repoDonacionDinero = RepoDonacionDinero.INSTANCE;
    RepoColaborador repoColaborador = RepoColaborador.INSTANCE;

    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarJuridicoOHumano(context, model);
        if(usuario == null) usuario= verificarAdmin(context, model);

        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        List<DonacionDinero> donacionesDineroNormal = colaborador.getColaboracionesRealizadas().stream()
                .filter(c -> c instanceof DonacionDinero)
                .map(c -> (DonacionDinero) c)
                .toList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato para la fecha
        List<DonacionDineroOutputDTO> donacionesDinero = new ArrayList<>();

        for (DonacionDinero donacion : donacionesDineroNormal) {
            DonacionDineroOutputDTO dto = new DonacionDineroOutputDTO();

            dto.setId(donacion.getId());
            dto.setFechaDonacion(dateFormat.format(donacion.getFechaColaboracion()));
            dto.setMonto(String.format("%.2f", donacion.getMonto()));
            dto.setEsPeriodica(donacion.getEsDonacionMensual() ? "Sí" : "No");
            dto.setRenovacion(donacion.getEsDonacionMensual());
            donacionesDinero.add(dto);
        }

        model.put("donacionesDinero", donacionesDinero);
        model.put("title", "Donar dinero");
        context.render("donaciones/donacionDinero.hbs", model);
    }

    @Override
    public void save(Context context) {

        Long idUsuario = context.sessionAttribute("usuario_id");
        Colaborador colaborador = repoColaborador.buscarPorIdUsuario(idUsuario);

        DonacionDinero donacionDinero = new DonacionDinero();
        donacionDinero.setMonto(Float.valueOf(Objects.requireNonNull(context.formParam("monto"))));

        String fechaStr = Objects.requireNonNull(context.formParam("fechaDonacion"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaDonacion = formatter.parse(fechaStr);
            donacionDinero.setFechaColaboracion(fechaDonacion);

            // Verificar si la donación es periódica
            donacionDinero.setEsDonacionMensual("true".equals(context.formParam("esPeriodica")));
            if (donacionDinero.getEsDonacionMensual()) {
                // la fechaPeriodica es la que va a ir cambiando cuando se crean nuevas instancias, usando la cronJob
                donacionDinero.setFechaColaboracion(fechaDonacion);
            }
            donacionDinero.setTipoColaboracion(TipoColaboracion.DINERO);
            donacionDinero.setMultiplicador(ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero());
            colaborador.agregarColaboracionRealizada(donacionDinero);
            // Guardar la donación
            repoDonacionDinero.agregar(donacionDinero);
            context.redirect("/donacionDinero");
        } catch (ParseException e) {
            // Manejar el error de parseo de fecha
            context.status(400).result("Formato de fecha inválido");
        }
    }

    public void cancelar(Context context) {
        Optional<DonacionDinero> donacionDinero = Optional.ofNullable(repoDonacionDinero.buscar(Long.valueOf(context.pathParam("id"))));
        if (donacionDinero.isPresent()) {
            donacionDinero.get().setEsDonacionMensual(false);
            repoDonacionDinero.modificar(donacionDinero.get());
            context.redirect("/donacionDinero");
        } else {
            context.status(404).result("Donación no encontrada");
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

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

}