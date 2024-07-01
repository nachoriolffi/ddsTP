package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoReporte;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoReporte implements IRepoReporte {

    //@Getter
    //private static RepoReporte instancia = null;
    @Getter
    private List<Reporte> reportes;

   // public static RepoReporte getInstancia() {
   //     if (instancia == null) {
   //         instancia = new RepoReporte();
   //     }
   //     return instancia;
   // }

    public RepoReporte() {
        this.reportes = new ArrayList<Reporte>();
    }
    public void agregarReporte(Reporte reporte) {
        this.reportes.add(reporte);    }

    public void eliminarReporte(Reporte reporte) {
        this.reportes.remove(reporte);
    }

    public void borrarReportes() {
        this.reportes.clear();
    }

}
