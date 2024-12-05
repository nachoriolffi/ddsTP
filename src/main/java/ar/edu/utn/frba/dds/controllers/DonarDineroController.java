package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.dtos.outputs.DonacionDineroOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionDinero;
import ar.edu.utn.frba.dds.services.UserService;

import ar.edu.utn.frba.dds.server.Server;

import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DonarDineroController extends BaseController implements ICrudViewsHandler {

    RepoDonacionDinero repoDonacionDinero = RepoDonacionDinero.INSTANCE;
    RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
    UserService userService = new UserService();
    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarJuridicoOHumano(context, model);

        if(usuario == null) usuario= verificarAdmin(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
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
        System.out.println("Inicio del método save");

        // Obtener el usuario logueado
        Long idUsuario = context.sessionAttribute("usuario_id");
        System.out.println("ID del usuario en la sesión: " + idUsuario);

        Colaborador colaborador = repoColaborador.buscarPorIdUsuario(idUsuario);
        System.out.println("Colaborador encontrado: " + colaborador);

        // Crear nueva donación de dinero
        DonacionDinero donacionDinero = new DonacionDinero();

        try {
            // Obtener el monto de la donación
            String montoStr = context.formParam("monto");
            System.out.println("Monto recibido del formulario: " + montoStr);

            Float monto = Float.valueOf(Objects.requireNonNull(montoStr));
            donacionDinero.setMonto(monto);
            System.out.println("Monto configurado: " + donacionDinero.getMonto());

            // Obtener y formatear la fecha de la donación
            String fechaStr = Objects.requireNonNull(context.formParam("fechaDonacion"));
            System.out.println("Fecha recibida del formulario: " + fechaStr);

            // Cambiar el formato de fecha si es necesario (ejemplo: yyyy-MM-dd para formularios HTML)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDonacion = dateFormat.parse(fechaStr);
            donacionDinero.setFechaColaboracion(fechaDonacion);
            System.out.println("Fecha de colaboración configurada: " + fechaDonacion);

            // Verificar si la donación es periódica
            String esPeriodicaStr = context.formParam("esPeriodica");
            System.out.println("¿Es donación periódica?: " + esPeriodicaStr);

            boolean esPeriodica = "true".equals(esPeriodicaStr);
            donacionDinero.setEsDonacionMensual(esPeriodica);

            if (esPeriodica) {
                System.out.println("La donación es periódica. Configurando fecha periódica...");
                donacionDinero.setFechaColaboracion(fechaDonacion);
            }

            // Configurar el tipo de colaboración y multiplicador
            donacionDinero.setTipoColaboracion(TipoColaboracion.DINERO);
            //double multiplicador = ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero();
            donacionDinero.setMultiplicador(1.0);
            System.out.println("Tipo de colaboración: " + donacionDinero.getTipoColaboracion());
            System.out.println("Multiplicador configurado: " + 1.0);/**/

            // Asociar la donación al colaborador
            colaborador.agregarColaboracionRealizada(donacionDinero);
            System.out.println("Donación asociada al colaborador: " + colaborador);

            // Guardar la donación
            repoDonacionDinero.agregar(donacionDinero);
            System.out.println("Donación guardada en el repositorio");

            // Redirigir después de guardar
            context.redirect("/donacionDinero");
            System.out.println("Redirección exitosa a /donacionDinero");

        } catch (ParseException e) {
            // Manejo de error de parseo de fecha
            System.err.println("Error al parsear la fecha: " + e.getMessage());
            context.status(400).result("Formato de fecha inválido");
        } catch (Exception e) {
            // Manejo de errores generales
            System.err.println("Error en el método save: " + e.getMessage());
            e.printStackTrace();
            context.status(500).result("Ocurrió un error al guardar la donación");
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