package ar.edu.utn.frba.dds.exportadorPDF;

import ar.edu.utn.frba.dds.repositorios.RepoReporte;

import java.io.FileNotFoundException;
import java.util.List;

public class Exportador {

    private void exportar( Exportable exportable, EstrategiaExportacion estrategia) throws FileNotFoundException {

        Reporte reporte = estrategia.exportar(exportable);
        // Agregamos el reporte al repoReporte
        RepoReporte.getInstancia().agregarReporte(reporte);
    }

}


