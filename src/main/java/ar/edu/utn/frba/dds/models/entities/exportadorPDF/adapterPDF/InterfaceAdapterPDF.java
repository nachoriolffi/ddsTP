package ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface InterfaceAdapterPDF {

    public Reporte exportar(Exportable... exportable) throws IOException;

}
