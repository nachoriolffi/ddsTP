package ar.edu.utn.frba.dds.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.exportadorPDF.Reporte;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class AdapterPDF implements InterfaceAdapterPDF{

        private String pathDocumento="C:\\Users\\user\\Desktop\\exportar.PDF";

    public static void exportToPdf(String dest, List<Exportable> exportables) throws FileNotFoundException {

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Para cada sección de datos, añade un título y una tabla al documento
        for (int i = 0; i < exportables.size(); i++) {
            document.add(new Paragraph("Sección " + (i + 1)));
            addTableToDocument(exportables.get(i).datos(), document);
        }

        document.close();
    }

    public static void addTableToDocument(Map<String, List<String>> data, Document document) {
        // Crear una tabla con tantas columnas como llaves en el mapa
        if(data == null){
            System.out.println("No hay nada para agregar al pdf");
        }
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

        // Añadir la tabla al documento
        document.add(table);
    }


    @Override
    public Reporte exportar(Exportable ...exportables) throws FileNotFoundException {
        exportToPdf(pathDocumento, List.of(exportables));
        return new Reporte(pathDocumento);
    }


}



