package ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;

import java.io.FileNotFoundException;

public interface InterfaceAdapterPDF {

    public Reporte exportar(Exportable... exportable) throws FileNotFoundException;

}
