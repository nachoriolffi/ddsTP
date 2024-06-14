package ar.edu.utn.frba.dds.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.exportadorPDF.Exportable;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class AdapterPDF implements InterfaceAdapterPDF{
        public void Exportar(Exportable exportable) throws FileNotFoundException {
            exportToPdf("C:\\Users\\Usuario\\Desktop\\exportar.pdf", exportable.datos());
            addTableToDocument(exportable.datos(), new Document(new PdfDocument(new PdfWriter("C:\\Users\\Usuario\\Desktop\\exportar.pdf"))));
        }


    public static void exportToPdf(String dest, Map<String, List<String>>... sections) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Para cada sección de datos, añade un título y una tabla al documento
        for (int i = 0; i < sections.length; i++) {
            document.add(new Paragraph("Sección " + (i + 1)));
            addTableToDocument(sections[i], document);
        }

        document.close();
    }

    public static void addTableToDocument(Map<String, List<String>> data, Document document) {
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

        // Añadir la tabla al documento
        document.add(table);
    }
}



