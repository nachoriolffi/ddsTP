package ar.edu.utn.frba.dds.exportadorPDF;

import lombok.Getter;

import java.util.List;
import java.util.Map;

public class Documento implements Exportable {

    @Getter
    private Map<String, List<String>> datos;


}
