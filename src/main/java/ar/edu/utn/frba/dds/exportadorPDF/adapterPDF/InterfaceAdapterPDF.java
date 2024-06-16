package ar.edu.utn.frba.dds.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.exportadorPDF.Reporte;

import java.io.FileNotFoundException;

public interface InterfaceAdapterPDF {

    public Reporte exportar(Exportable exportable) throws FileNotFoundException;

}
