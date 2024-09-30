package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncargarseHeladeraController implements ICrudViewsHandler {

    private RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;

    @Override
    public void index(Context context) {
        List<Heladera> heladeras = this.repoHeladeras.buscarTodos();
        List<HeladeraOutputDTO> heladeraOutputDTOS = new ArrayList<>();
        int i = 50;
        for (Heladera heladera : heladeras) {
            HeladeraOutputDTO heladeraOutputDTO = new HeladeraOutputDTO();
            heladeraOutputDTO.setNombre(heladera.getNombre());
            heladeraOutputDTO.setCapacidad(heladera.getModelo().getCantidadMaximaDeViandas());
            heladeraOutputDTO.setDireccion("Avenida Medrano " + String.valueOf(i));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            heladeraOutputDTO.setFechaPuestaFunc(sdf.format(heladera.getFechaPuestaFunc()));
            heladeraOutputDTOS.add(heladeraOutputDTO);
            i += 100;
        }
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Encargarse De Heladera");
        model.put("heladeras", heladeraOutputDTOS);
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
