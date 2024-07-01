package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.DocumentoFallasHeladera;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF.ExportarAPdf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class TestExportadorPdf {

    private DocumentoFallasHeladera exportable1;
    private DocumentoFallasHeladera exportable2;
    private DocumentoFallasHeladera exportable3;
    Map<String, List<String>> fallasHeladeras;
    Map<String, List<String>> viandasRetCol;
    Map<String, List<String>> viandasColaborador;

    @BeforeEach
    public void setUp() {

        fallasHeladeras = Map.of(
                "HELADERA", List.of("HELADERA1", "HELADERA2"),
                "CANTIDAD", List.of("55", "60")
        );
        viandasRetCol = Map.of(
                "HELADERA", List.of("HELADERA1"),
                "COLOCADAS", List.of("123131333"),
                "RETIRADAS", List.of("12312313")
        );
        viandasColaborador = Map.of(
                "COLABORADOR", List.of("COLABORADOR1"),
                "CANTIDAD", List.of("100")
        );
        exportable1 = new DocumentoFallasHeladera(fallasHeladeras);
        exportable2= new DocumentoFallasHeladera(viandasRetCol);
        exportable3 = new DocumentoFallasHeladera(viandasColaborador);
    }

    @Test
    public void testExportarPdf() throws FileNotFoundException {
        // Exportar a PDF

        ExportarAPdf exportarAPdf = new ExportarAPdf();
        exportarAPdf.exportar(exportable1, exportable2, exportable3);


    }
}