package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF.ExportarAPdf;

import java.io.FileNotFoundException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

public class CronJobReporte {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("EJEMPLO");
        FactoryDocumento factory = new FactoryDocumento();
        List<Exportable> exportables= new ArrayList<Exportable>();
        System.out.println("Se crean instancias de factory y exportables");
        //pido que me creee cada documento
        for(TipoDocumento doc: TipoDocumento.values()){
            Exportable exportable = factory.crearDocumento(doc);
            exportable.generarDocumento();
           exportables.add( exportable);
        }
        System.out.println("Sali dle for");
        // ya tenemos los exportables ahora necesitamos que se genere el documento
        //ExportarAPdf exportador = new ExportarAPdf();
        Exportador exportador = new Exportador();
        System.out.println("Creo el exportador");
        exportador.exportar(exportables, new ExportarAPdf());
        //exportador.exportar(exportables.toArray(new Exportable[0]));
        System.out.println("Se generaron los reportes en PDF");
    }
}
