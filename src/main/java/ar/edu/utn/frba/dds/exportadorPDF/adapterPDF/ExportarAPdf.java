package ar.edu.utn.frba.dds.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.exportadorPDF.EstrategiaExportacion;
import ar.edu.utn.frba.dds.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.exportadorPDF.Reporte;

import java.io.FileNotFoundException;

public class ExportarAPdf implements EstrategiaExportacion {

    private InterfaceAdapterPDF adapterPDF;

    public ExportarAPdf(InterfaceAdapterPDF adapterPDF) {
        this.adapterPDF = adapterPDF;
    }

    @Override
    public Reporte exportar(Exportable exportable) throws FileNotFoundException {
      return  adapterPDF.exportar(exportable);

   }


}
