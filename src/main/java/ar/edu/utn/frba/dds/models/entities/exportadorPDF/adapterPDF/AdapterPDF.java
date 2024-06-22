package ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class AdapterPDF implements InterfaceAdapterPDF {

    private String pathDocumento = "C:\\Users\\Nacho\\Desktop\\exportar.PDF";

    public void exportToPdf(String dest, List<Exportable> exportables) throws FileNotFoundException {
        File file = new File(dest);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // Crea el directorio si no existe
        }

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

            document.add(new Paragraph("Sección"));
            addTableToDocument(datos, document);
        }

        document.close(); // Cierra el documento al finalizar todas las iteraciones
    }


    public static void addTableToDocument(Map<String, List<String>> data, Document document) {
        if (data == null || data.isEmpty()) {
            return; // No hacer nada si los datos son nulos o vacíos
        }

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
                table.addCell(data.get(key).get(i));
            }
        }

        document.add(table); // Agregar la tabla al documento después de llenar todas las filas
    }

    @Override
    public Reporte exportar(Exportable... exportables) throws FileNotFoundException {
        exportToPdf(pathDocumento, List.of(exportables));
        return new Reporte(pathDocumento);
    }
}




