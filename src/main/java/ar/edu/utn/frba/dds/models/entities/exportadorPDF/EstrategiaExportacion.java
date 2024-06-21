package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import java.io.FileNotFoundException;

public interface EstrategiaExportacion {
    public Reporte exportar(Exportable... exportable) throws FileNotFoundException;
}
