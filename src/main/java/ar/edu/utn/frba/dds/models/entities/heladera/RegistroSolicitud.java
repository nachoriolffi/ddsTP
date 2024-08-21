package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroSolicitud {

    private Date fechaSolicitud;
    @Setter
    private Tarjeta tarjeta;
    private TipoSolicitud solicitud;
    private Boolean realizada;
    private List<Vianda> cantidadViandas;
    private Boolean retiroVianda;

    private Heladera heladeraAIr;


    public RegistroSolicitud(Tarjeta tarjeta){
        this.tarjeta = tarjeta;
    }

    public RegistroSolicitud(){
        this.fechaSolicitud = new Date();
        this.cantidadViandas = new ArrayList<Vianda>();
    }




    public Tarjeta getTarjeta() {
        return tarjeta;
    }
}
