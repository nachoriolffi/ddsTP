package ar.edu.utn.frba.dds.exportadorPDF;

import java.util.List;
import java.util.Map;

public interface Exportable {

    default Map<String, List<String>> datos(){
        return null;
    };

}
