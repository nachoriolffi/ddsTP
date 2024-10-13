package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.outputs.DonacionDineroOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionDinero;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DonarDineroController extends BaseController implements ICrudViewsHandler {

    RepoDonacionDinero repoDonacionDinero = RepoDonacionDinero.INSTANCE;

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();

        List<DonacionDinero> donacionesDineroNormal = repoDonacionDinero.buscarTodos();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato para la fecha
        List<DonacionDineroOutputDTO> donacionesDinero = new ArrayList<>();

        for (DonacionDinero donacion : donacionesDineroNormal) {
            DonacionDineroOutputDTO dto = new DonacionDineroOutputDTO();

            dto.setId(donacion.getId());

            // Convertir la fecha a String
            dto.setFechaDonacion(dateFormat.format(donacion.getFechaColaboracion()));

            // Convertir el monto a String con formato
            dto.setMonto(String.format("%.2f", donacion.getMonto()));

            // Convertir esPeriodica (booleano) a "Sí" o "No"
            dto.setEsPeriodica(donacion.getDonacionMensual() ? "Sí" : "No");

            dto.setRenovacion(donacion.getDonacionMensual());

            // Agregar el dto a la lista de salida
            donacionesDinero.add(dto);
        }

        model.put("donacionesDinero",donacionesDinero);


        model.put("title", "Donar dinero");
        context.render("donaciones/donacionDinero.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        DonacionDinero donacionDinero = new DonacionDinero();

        // Convertir el monto
        donacionDinero.setMonto(Float.valueOf(Objects.requireNonNull(context.formParam("monto"))));

        // Formatear la fecha correctamente
        String fechaStr = Objects.requireNonNull(context.formParam("fechaDonacion"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaDonacion = formatter.parse(fechaStr);
            donacionDinero.setFechaColaboracion(fechaDonacion);

            // Verificar si la donación es periódica
            donacionDinero.setDonacionMensual("true".equals(context.formParam("esPeriodica")));

            if (donacionDinero.getDonacionMensual()) {
                donacionDinero.setFechaPeriodica(fechaDonacion);
            }

            // Configuración adicional
            donacionDinero.setTipoColaboracion(TipoColaboracion.DINERO);
            donacionDinero.setMultiplicador(ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero());

            // Guardar la donación
            repoDonacionDinero.agregar(donacionDinero);

            // Redireccionar
            context.redirect("/donacionDinero");
        } catch (ParseException e) {
            // Manejar el error de parseo de fecha
            context.status(400).result("Formato de fecha inválido");
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

    public void cancelar(Context context) {
        Optional<DonacionDinero> donacionDinero = Optional.ofNullable(repoDonacionDinero.buscar(Long.valueOf(context.pathParam("id"))));
        if(donacionDinero.isPresent()) {
            donacionDinero.get().setDonacionMensual(false);
            repoDonacionDinero.modificar(donacionDinero.get());
            context.redirect("/donacionDinero");
        }else {
            context.status(404).result("Donación no encontrada");
        }

    }

}
