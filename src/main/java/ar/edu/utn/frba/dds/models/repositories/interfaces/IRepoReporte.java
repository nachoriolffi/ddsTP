package ar.edu.utn.frba.dds.models.repositories.interfaces;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;

public interface IRepoReporte {

    void agregarReporte(Reporte reporte);

    void eliminarReporte(Reporte reporte);

    void borrarReportes();

}
