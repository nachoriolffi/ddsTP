package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

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