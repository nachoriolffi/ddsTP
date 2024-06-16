package ar.edu.utn.frba.dds.exportadorPDF;

import java.io.FileNotFoundException;

public interface EstrategiaExportacion {
    public Reporte exportar(Exportable exportable) throws FileNotFoundException;
}
