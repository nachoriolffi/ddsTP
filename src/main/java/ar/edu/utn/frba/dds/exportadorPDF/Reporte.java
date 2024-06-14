package ar.edu.utn.frba.dds.exportadorPDF;

import java.util.Date;

public class Reporte {

    private String pathDocumento;

    private Date fechaCreacion;

    public Reporte(String pathDocumento, Date fechaCreacion) {
        this.pathDocumento = pathDocumento;
        this.fechaCreacion = fechaCreacion;
    }

    public Reporte() {
    }
}
