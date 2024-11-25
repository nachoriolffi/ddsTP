package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.PuntoRecomendadoDTO;
import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.HacerseCargoDeHeladera;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.recomendacionPuntos.AServicioRecomendacionPuntos;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.Georef;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.GeorefService;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.services.HeladeraService;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EncargarseHeladeraController extends BaseController implements ICrudViewsHandler {

    private RepoDireccion repoDireccion = RepoDireccion.INSTANCE;
    private RepoCalle repoCalle = RepoCalle.INSTANCE;
    private UserService userService = new UserService();
    private HeladeraService heladeraService = new HeladeraService();
    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarJuridico(context, model);
        UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
        model.put("usuario", usuarioDTO);
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        List<HeladeraOutputDTO> heladeraOutputDTOS = new ArrayList<>();
        List<Heladera> heladeras = colaborador.getColaboracionesRealizadas().stream()
                .filter(c -> c instanceof HacerseCargoDeHeladera)
                .map(c -> ((HacerseCargoDeHeladera) c).getHeladera())
                .toList();
        for (Heladera heladera : heladeras) {
            HeladeraOutputDTO heladeraOutputDTO = new HeladeraOutputDTO();
            heladeraOutputDTO.setNombre(heladera.getNombre());
            heladeraOutputDTO.setCapacidad(heladera.getModelo().getCantidadMaximaDeViandas());
            heladeraOutputDTO.setDireccion(heladera.getDireccion().getCalle().getCalle());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            heladeraOutputDTO.setFechaPuestaFunc(sdf.format(heladera.getFechaPuestaFunc()));
            heladeraOutputDTOS.add(heladeraOutputDTO);
        }
        List<ModeloHeladera> modelos = RepoModelo.INSTANCE.buscarTodos();
        model.put("modelos", modelos);
        model.put("heladeras", heladeraOutputDTOS);

        model.put("title", "Encargarse De Heladera");
        context.render("donaciones/encargarseDeHeladera.hbs", model);


    }

    @Override
    public void save(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarJuridico(context,model);
        HeladeraInputDTO heladeraInputDTO = new HeladeraInputDTO();
        heladeraInputDTO.setPiso(context.formParam("piso"));
        heladeraInputDTO.setCalle(context.formParam("calle"));
        heladeraInputDTO.setLatitud(context.formParam("latitud"));
        heladeraInputDTO.setLongitud(context.formParam("longitud"));
        heladeraInputDTO.setNombre(context.formParam("nombre"));
        heladeraInputDTO.setAltura(context.formParam("altura"));
        heladeraInputDTO.setModelo(context.formParam("modelo"));

        Heladera heladera=heladeraService.darDeAltaHeladera(heladeraInputDTO);
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        HacerseCargoDeHeladera hacerseCargoDeHeladera = new HacerseCargoDeHeladera();
        hacerseCargoDeHeladera.setFechaColaboracion(new Date());
        hacerseCargoDeHeladera.setHeladera(heladera);
        hacerseCargoDeHeladera.setTipoColaboracion(TipoColaboracion.HACERSE_CARGO_HELADERA);
        hacerseCargoDeHeladera.setColaborador(colaborador);
        RepoHacerceCargoHeladera.INSTANCE.agregar(hacerseCargoDeHeladera);
        colaborador.agregarColaboracionRealizada(hacerseCargoDeHeladera);
        RepoColaborador.INSTANCE.modificar(colaborador);
        context.redirect("/encargarseHeladera");
    }

    public void searchPoints(Context context) throws IOException {
        String direccion = context.formParam("direccion");
        String altura = context.formParam("altura");
        String radio = context.formParam("radio");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.datos.gob.ar/georef/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GeorefService georefService = retrofit.create(GeorefService.class);
        Georef georef = new Georef(georefService);
        Coordenada coordenada = georef.obtenerCoordenadasPorDireccion(direccion);
        AServicioRecomendacionPuntos aServicioRecomendacionPuntos = new AServicioRecomendacionPuntos();
        List<Coordenada> coordenadas = aServicioRecomendacionPuntos.recomendarPuntos(coordenada.getLongitud(), coordenada.getLatitud(), Integer.valueOf(radio));

        Map<String, Object> model = new HashMap<>();
        // TODO List<PuntoRecomendadoDTO> direcciones = georef.obtenerCallesPorCoordenadas(coordenadas);
        // TODO model.put("direcciones", direcciones);

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
