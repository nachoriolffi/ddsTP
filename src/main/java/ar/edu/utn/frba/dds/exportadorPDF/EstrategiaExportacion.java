package ar.edu.utn.frba.dds.exportadorPDF;

import java.io.FileNotFoundException;
import java.util.List;

public interface EstrategiaExportacion {
    public Reporte exportar(Exportable... exportable) throws FileNotFoundException;
}
