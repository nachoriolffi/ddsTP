package ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReporte;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class AdapterPDF implements InterfaceAdapterPDF {

    List<String> secciones = new ArrayList<>();

    Path projectBasePath = Paths.get(System.getProperty("user.dir"));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");  // Formato de fecha
    String formattedDate = LocalDateTime.now().format(formatter);

    Path pathToPdf = projectBasePath.resolve("src/main/resources/public/pdfs/reporte_" + formattedDate + ".pdf");

    public void exportToPdf(String dest, List<Exportable> exportables) throws IOException {

        File file = new File(dest);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            if (!created) {
                throw new IOException("No se pudo crear el directorio para el archivo PDF.");
            }
        }

        this.secciones.add("Fallas Por Heladera");
        this.secciones.add("Viandas Retiradas/Colocadas Por Heladera");
        this.secciones.add("Cantidad de Viandas Donadas Por Colaborador");

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Para cada sección de datos, añade un título y una tabla al documento
        for (int i = 0; i < exportables.size(); i++) {
            Exportable exportable = exportables.get(i);
            Map<String, List<String>> datos = exportable.datos();

            // Añade una nueva página para cada exportable excepto el primero
            if (i > 0) {
                pdf.addNewPage();
                document.add(new AreaBreak());
            }

            document.add(new Paragraph(secciones.get(i)));
            addTableToDocument(datos, document);
        }

        document.close(); // Cierra el documento al finalizar todas las iteraciones
    }


    public static void addTableToDocument(Map<String, List<String>> data, Document document) {
        if (data == null || data.isEmpty()) {
            System.out.println("No hay datos para exportar");
            return; // No hacer nada si los datos son nulos o vacíos

        }
        System.out.println("Exportando datos a PDF");
        // Crear una tabla con tantas columnas como llaves en el mapa
        Table table = new Table(UnitValue.createPercentArray(data.keySet().size()));
        table.setWidth(UnitValue.createPercentValue(100));

        // Añadir encabezados
        for (String key : data.keySet()) {
            table.addHeaderCell(key);
        }

        // Determinar la cantidad de filas
        int numRows = data.values().iterator().next().size();

        // Añadir los datos
        for (int i = 0; i < numRows; i++) {
            for (String key : data.keySet()) {
                String cellValue = data.get(key).get(i);
                if (cellValue != null) {
                    table.addCell(cellValue);
                } else {
                    table.addCell("N/A"); // or whatever you want to represent null values
                }
            }
        }
        document.add(table); // Agregar la tabla al documento después de llenar todas las filas
    }

    @Override
    public Reporte exportar(Exportable... exportables) throws IOException {
        exportToPdf(pathToPdf.toString(), List.of(exportables));
        Reporte reporte = new Reporte(pathToPdf.toString());
        RepoReporte.INSTANCE.agregar(reporte);
        return reporte;
    }
}




