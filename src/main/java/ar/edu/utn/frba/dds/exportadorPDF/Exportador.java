package ar.edu.utn.frba.dds.exportadorPDF;

import java.io.FileNotFoundException;

public class Exportador {

    private void exportar(Exportable exportable, EstrategiaExportacion estrategia) throws FileNotFoundException {

        Reporte reporte = estrategia.exportar(exportable);
        // debemos guardar el reporte en un repo
        
    }

}


