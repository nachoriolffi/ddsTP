package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases;
import ar.edu.utn.frba.dds.dtos.CalleDTO;
import java.util.List;

public class ListadoCalles {
    private List<CalleDTO> calles;

    // Getter y Setter
    public List<CalleDTO> getCalles() {
        return calles;
    }

    public void setCalles(List<CalleDTO> calles) {
        this.calles = calles;
    }
}
