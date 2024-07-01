package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import java.util.List;
import java.util.Map;

public interface Exportable {

    public Map<String, List<String>> datos();

    public void generarDocumento();

}
