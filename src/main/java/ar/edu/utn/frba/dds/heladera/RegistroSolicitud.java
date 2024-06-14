package ar.edu.utn.frba.dds.heladera;

import ar.edu.utn.frba.dds.tarjeta.Tarjeta;

import java.util.Date;

public class RegistroSolicitud {

    private Date fechaCreacionSolicitud;

    private Tarjeta tarjeta;

    private Boolean realizada;

    private TipoSolicitud tipoSolicitud;

    public RegistroSolicitud(Tarjeta tarjeta, TipoSolicitud tipoSolicitud) {
        this.fechaCreacionSolicitud = new Date();
        this.tarjeta = tarjeta;
        this.realizada = false;
        this.tipoSolicitud = tipoSolicitud;
    }
}
