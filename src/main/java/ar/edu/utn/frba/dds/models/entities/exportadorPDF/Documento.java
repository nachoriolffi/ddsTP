package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import lombok.Getter;
import org.glassfish.grizzly.utils.StringFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.IteratorUtils.forEach;

public class Documento implements Exportable {

    @Getter
    private Map<String, List<String>> datos;

    private RepoHeladeras repoHeladeras ;



    public Documento(Map<String, List<String>> datos) {
        this.datos = datos;
    }

    public Documento(RepoHeladeras repoHeladeras) {
        this.repoHeladeras = repoHeladeras;
        this.datos = new HashMap<String, List<String>>();
    }

    @Override
    public Map<String, List<String>> datos() {
        return this.getDatos();
    }


    public Map<String, List<String>> formarDocumento() {
        for(Heladera heladera : repoHeladeras.traerHeladeras()){
            if(!heladera.getIncidentes().isEmpty()) {
                List<Incidente> incidentes = new ArrayList<Incidente>();
                for (Incidente incidente : heladera.getIncidentes()) {
                    if(incidente.getTipoIncidente().equals(TipoIncidente.FALLA)){
                        incidentes.add(incidente);
                    }
                }
                List<String> list = new ArrayList<>();
                list.add(String.valueOf(incidentes.size()));
                datos.put(heladera.getNombre(), list);
            }
        }

        Map<String, List<String>> fallasHeladeras = new HashMap<>();
        List<String> heladeras = new ArrayList<>(datos.keySet());
        List<String> cantidades = datos.values().stream()
                .map(list -> list.get(0)) // asumiendo que cada lista tiene al menos un elemento
                .collect(Collectors.toList());

        fallasHeladeras.put("HELADERA", heladeras);
        fallasHeladeras.put("CANTIDAD", cantidades);

        return fallasHeladeras;
    }
}