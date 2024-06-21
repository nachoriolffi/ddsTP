package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;

import java.util.Date;

public class RegistroSolicitud {

    private Date fechaCreacionSolicitud;
    private Tarjeta tarjeta;
    private TipoSolicitud solicitud;
    private Boolean realizada;
}
