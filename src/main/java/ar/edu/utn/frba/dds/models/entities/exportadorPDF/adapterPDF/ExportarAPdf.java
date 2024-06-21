package ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.EstrategiaExportacion;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;

import java.io.FileNotFoundException;

public class ExportarAPdf implements EstrategiaExportacion {

    private InterfaceAdapterPDF adapterPDF;

    public ExportarAPdf() {
        this.adapterPDF = new AdapterPDF();
    }

    @Override
    public Reporte exportar(Exportable... exportable) throws FileNotFoundException {
      return  adapterPDF.exportar(exportable);

   }


}
