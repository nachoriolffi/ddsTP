package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentoFallasHeladera implements Exportable {

    @Getter
    private Map<String, List<String>> datos;

    private RepoHeladeras repoHeladeras ;

    public DocumentoFallasHeladera(Map<String, List<String>> datos) {
        this.datos = datos;
    }

    public DocumentoFallasHeladera() {
        this.repoHeladeras = RepoHeladeras.INSTANCE;
        this.datos = new HashMap<String, List<String>>();
        generarDocumento();
    }

    @Override
    public Map<String, List<String>> datos() {
        return this.getDatos();
    }

    @Override
    public void generarDocumento() {

        List<String> heladerasNombre = new ArrayList<String>(); // guarda nombre de healderas
        List<String> fallas = new ArrayList<String>(); // guarda cantidad de falla por heladera

        for(Heladera heladera : repoHeladeras.INSTANCE.buscarTodos()) // SE RECIBEN TODAS LAS HELADERAS Y SE TOMA UNA A LA VEZ
        {
            if(!heladera.getIncidentes().isEmpty()) // SI TIENE INCIDENTES
            {
                heladerasNombre.add(heladera.getNombre()); // AGREGO EL NOMBRE DE LA HELADERA
                List<Incidente> incidentes = new ArrayList<Incidente>();
                for (Incidente incidente : heladera.getIncidentes()) // AGARRO LOS INCIDENTES DE LA HELADERA
                {
                    if(incidente.getTipoIncidente().equals(TipoIncidente.FALLA)){
                        incidentes.add(incidente);
                    }
                }
               fallas.add(String.valueOf(incidentes.size())); // AGREGO LA CANTIDAD DE FALLAS

            }
        }

        this.datos.put("Heladera", heladerasNombre);
        this.datos.put("Cantidad Fallas", fallas);

    }
}
