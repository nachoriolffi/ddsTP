package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroSolicitud {

    private Date fechaSolicitud;
    private Tarjeta tarjeta;
    private TipoSolicitud solicitud;
    private Boolean realizada;
    private List<Vianda> cantidadViandas;
    private Boolean retiroVianda;


    public RegistroSolicitud(){
        this.fechaSolicitud = new Date();
        this.cantidadViandas = new ArrayList<Vianda>();
    }

}
