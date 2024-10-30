package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF.ExportarAPdf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CronJobReporte {

    public void ejecutarReporte() {
        System.out.println("Iniciando generación de reportes...");

        try {
            FactoryDocumento factory = new FactoryDocumento();
            List<Exportable> exportables = new ArrayList<>();
            System.out.println("Se crean instancias de factory y exportables");

            // Crear cada documento según su tipo
            for (TipoDocumento doc : TipoDocumento.values()) {
                Exportable exportable = factory.crearDocumento(doc); // el factory salva vidas
                exportable.generarDocumento();
                exportables.add(exportable);
            }
            System.out.println("Sali del for");

            // Crear el exportador y generar los reportes en PDF
            Exportador exportador = new Exportador();
            System.out.println("Creo el exportador");
            exportador.exportar(exportables, new ExportarAPdf());
            System.out.println("Se generaron los reportes en PDF");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
