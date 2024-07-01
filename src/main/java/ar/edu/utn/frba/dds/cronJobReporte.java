package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportador;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.FactoryDocumento;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.TipoDocumento;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF.ExportarAPdf;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class cronJobReporte {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("EJEMPLO");
        FactoryDocumento factory = new FactoryDocumento();
        List<Exportable> exportables= new ArrayList<Exportable>();
        System.out.println("Se crean instancias de factory y exportables");
        //pido que me creee cada documento
        for(TipoDocumento doc: TipoDocumento.values()){
           exportables.add( factory.crearDocumento(doc));
        }
        System.out.println("Sali dle for");
        // ya tenemos los exportables ahora necesitamos que se genere el documento
        Exportador exportador = new Exportador();
        exportador.exportar(exportables, new ExportarAPdf());
        System.out.println("Se generaron los reportes en PDF");
    }
}
