package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;

import java.util.List;

public class RepoReporte extends RepoGenerico<Reporte> {

    public static RepoReporte INSTANCE = new RepoReporte();

    public RepoReporte() {
        super(Reporte.class);
    }

    @Override
    public List<Reporte> buscarTodos() {
        List<Reporte> reportes = super.buscarTodos();

        for (Reporte reporte : reportes) {
            // Calcular los valores antes de devolver la lista
            reporte.calcularSemana();
            reporte.calcularMes();
            reporte.calcularAnio();
        }

        return reportes;
    }

}
