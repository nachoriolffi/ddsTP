package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReporte;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Exportador {

    public void exportar(List<Exportable> exportables, EstrategiaExportacion estrategia) throws IOException {

        RepoReporte repo= new RepoReporte();


        Reporte reporte = estrategia.exportar(exportables.toArray(new Exportable[0]));
            // Agregamos el reporte al repoReporte
        repo.agregar(reporte);

    }


}


