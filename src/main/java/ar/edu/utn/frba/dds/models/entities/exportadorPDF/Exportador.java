package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReporte;

import java.io.FileNotFoundException;
import java.util.List;

public class Exportador {

    public void exportar(List<Exportable> exportables, EstrategiaExportacion estrategia) throws FileNotFoundException {

        RepoReporte repo= RepoReporte.getInstancia();

        for (Exportable exportable: exportables) {
            Reporte reporte = estrategia.exportar(exportable);
            // Agregamos el reporte al repoReporte
            RepoReporte.getInstancia().agregarReporte(reporte);
        }


    }


}


