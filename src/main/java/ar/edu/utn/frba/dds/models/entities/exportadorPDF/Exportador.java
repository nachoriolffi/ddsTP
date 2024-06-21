package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReporte;

import java.io.FileNotFoundException;

public class Exportador {

    private void exportar( Exportable exportable, EstrategiaExportacion estrategia) throws FileNotFoundException {

        Reporte reporte = estrategia.exportar(exportable);
        // Agregamos el reporte al repoReporte
        RepoReporte.getInstancia().agregarReporte(reporte);
    }

}


