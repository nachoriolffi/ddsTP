package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.exportadorPDF.Documento;
import ar.edu.utn.frba.dds.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.exportadorPDF.Exportador;
import ar.edu.utn.frba.dds.exportadorPDF.adapterPDF.ExportarAPdf;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.vianda.Vianda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class TestExportadorPdf {

    private Documento exportable1;
    private Documento exportable2;
    private Documento exportable3;
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
        exportable1 = new Documento(fallasHeladeras);
        exportable2= new Documento(viandasRetCol);
        exportable3 = new Documento(viandasColaborador);
    }

    @Test
    public void testExportarPdf() throws FileNotFoundException {
        // Exportar a PDF

        ExportarAPdf exportarAPdf = new ExportarAPdf();
        exportarAPdf.exportar(exportable1,exportable2,exportable3);

    }
}