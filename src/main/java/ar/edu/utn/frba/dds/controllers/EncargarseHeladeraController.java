package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.SimpleDateFormat;
import java.util.*;

public class EncargarseHeladeraController extends BaseController implements ICrudViewsHandler {

    private RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
    private RepoCoordenada repoCoordenada = RepoCoordenada.INSTANCE;
    private RepoDireccion repoDireccion = RepoDireccion.INSTANCE;
    private RepoCalle repoCalle = RepoCalle.INSTANCE;

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarJuridico(context, model);
        List<Heladera> heladeras = this.repoHeladeras.buscarTodos();
        List<HeladeraOutputDTO> heladeraOutputDTOS = new ArrayList<>();
        if (heladeras != null) {
            for (Heladera heladera : heladeras) {
                HeladeraOutputDTO heladeraOutputDTO = new HeladeraOutputDTO();
                heladeraOutputDTO.setNombre(heladera.getNombre());
                heladeraOutputDTO.setCapacidad(heladera.getModelo().getCantidadMaximaDeViandas());
                if (heladera.getDireccion() != null) {
                    Long idDireccion = heladera.getDireccion().getId();
                    Direccion direccion = repoDireccion.buscar(idDireccion);
                    Long idCalle = direccion.getCalle().getId();
                    String calle = repoCalle.buscar(idCalle).getCalle();
                    String direccionDTO;
                    if (direccion.getPiso() >= 1) {
                        direccionDTO = calle + " " + direccion.getAltura().toString() + " (Piso: " + direccion.getPiso() + ")";
                    } else {
                        direccionDTO = calle + " " + direccion.getAltura().toString();
                    }
                    heladeraOutputDTO.setDireccion(direccionDTO);
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                heladeraOutputDTO.setFechaPuestaFunc(sdf.format(heladera.getFechaPuestaFunc()));
                heladeraOutputDTOS.add(heladeraOutputDTO);
            }
            List<ModeloHeladera> modelos = RepoModelo.INSTANCE.buscarTodos();
            model.put("modelos", modelos);
            model.put("heladeras", heladeraOutputDTOS);
        }

        model.put("title", "Encargarse De Heladera");
        context.render("donaciones/encargarseDeHeladera.hbs", model);


    }

    @Override
    public void show(Context context) {
        // TODO CUANDO HAYA TIEMPO, no es tan importante por ahora
    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        Heladera heladera = new Heladera();
        ModeloHeladera modeloHeladera = RepoModelo.INSTANCE.buscar(Long.valueOf(Objects.requireNonNull(context.formParam("modeloHeladera"))));

        Coordenada coordenada = new Coordenada(-34.598630, -58.419962);
        repoCoordenada.agregar(coordenada);
        heladera.setCoordenada(coordenada);
        heladera.setModelo(modeloHeladera);
        heladera.setEstaActiva(true);
        heladera.setViandasDisponibles(modeloHeladera.getCantidadMaximaDeViandas());
        heladera.setNombre(context.formParam("nombreHeladera"));
        heladera.setFechaPuestaFunc(new Date());
        Direccion direccion = new Direccion();
        direccion.setAltura(Integer.valueOf(Objects.requireNonNull(context.formParam("altura"))));
        Calle calle = new Calle(context.formParam("calle"));
        repoCalle.agregar(calle);
        direccion.setCalle(calle);
        direccion.setPiso(Integer.valueOf(Objects.requireNonNull(context.formParam("piso"))));
        repoDireccion.agregar(direccion);
        heladera.setDireccion(direccion);
        heladera.setReceptorTemperatura(new ReceptorTemperatura());
        heladera.setReceptorMovimiento(new ReceptorMovimiento());
        this.repoHeladeras.agregar(heladera);
        context.redirect("/encargarseHeladera");
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
