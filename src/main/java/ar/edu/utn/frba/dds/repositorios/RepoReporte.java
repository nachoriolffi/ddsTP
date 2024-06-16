package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.exportadorPDF.Reporte;
import lombok.Getter;

import java.util.List;

public class RepoReporte {

    private static RepoReporte instancia = null;
    @Getter
    private List<Reporte> reportes;

    public static RepoReporte getInstancia() {
        if (instancia == null) {
            instancia = new RepoReporte();
        }
        return instancia;
    }

    public void agregarReporte(Reporte reporte) {
        this.reportes.add(reporte);
    }

    public void eliminarReporte(Reporte reporte) {
        this.reportes.remove(reporte);
    }

    public void borrarReportes() {
        this.reportes.clear();
    }

}
