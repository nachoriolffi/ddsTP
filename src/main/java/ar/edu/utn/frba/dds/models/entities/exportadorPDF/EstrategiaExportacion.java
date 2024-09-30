package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface EstrategiaExportacion {
    public Reporte exportar(Exportable... exportable) throws IOException;
}
