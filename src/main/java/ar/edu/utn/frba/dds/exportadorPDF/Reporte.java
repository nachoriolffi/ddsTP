package ar.edu.utn.frba.dds.exportadorPDF;

import ar.edu.utn.frba.dds.repositorios.RepoReporte;

import java.util.Date;

public class Reporte {

    private String pathDocumento;

    private Date fechaCreacion;

    public Reporte(String pathDocumento) {
        this.pathDocumento = pathDocumento;
        this.fechaCreacion = new Date();
    }

    public Reporte() {
    }
}
