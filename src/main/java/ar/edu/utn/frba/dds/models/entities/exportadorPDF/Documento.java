package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import lombok.Getter;

import java.util.List;
import java.util.Map;

public class Documento implements Exportable {

    @Getter
    private Map<String, List<String>> datos;

    public Documento(Map<String, List<String>> datos) {
        this.datos = datos;
    }

    @Override
    public Map<String, List<String>> datos() {
        return this.getDatos();
    }
}
