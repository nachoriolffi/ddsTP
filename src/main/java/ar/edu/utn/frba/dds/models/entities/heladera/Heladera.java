package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Heladera {

    private Direccion direccion;
    private Coordenada coordenada;
    private String nombre;
    private Integer capacidad;
    private Date fechaPuestaFunc;
    private List<Vianda> viandas;
    private ReceptorMovimiento receptorMovimiento;
    private ReceptorTemperatura receptorTemperatura;
    private List<Incidente> incidentes;
    private List<RegistroSolicitud>solicitudesApertura; // es una lista de avisos a la heladera para que se abra
    private List<RegistroApertura> aperturas; // es una lista de registros de aperturas que se hicieron
    private Boolean estaActiva;
    private ModeloHeladera modelo;
    private Double tempActual;

    public Heladera(){
        this.viandas = new ArrayList<>();
        this.incidentes = new ArrayList<>();
        this.solicitudesApertura = new ArrayList<>();
        this.aperturas = new ArrayList<>();
    }

    public Heladera(Direccion direccion, Coordenada coordenada, Integer capacidad, List<Vianda> viandas) {
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.capacidad = capacidad;
        this.viandas = viandas;
        this.incidentes= new ArrayList<Incidente>();
    }

    public Heladera(Direccion direccion, Coordenada coordenada, Integer capacidad, List<Vianda> viandas,Date fechaPuestaFunc) {
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.capacidad = capacidad;
        this.viandas = viandas;
        this.fechaPuestaFunc = fechaPuestaFunc;
    }

    public Heladera(Direccion direccion, Coordenada coordenada, Integer capacidad, Boolean estaActiva) {
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.capacidad = capacidad;
        this.estaActiva = estaActiva;
    }

    public void agregarVianda(Vianda vianda) {
        viandas.add(vianda);
    }

    public void agregarRegistroDeAlerta(Incidente registro) {
        incidentes.add(registro);
    }

    public void configurarTemperatura(Double nuevaTemp) {
        tempActual = nuevaTemp;
    }

    public long mesesActiva (Date fechaColaboracion) {
        return ( fechaColaboracion.getTime() - fechaPuestaFunc.getTime()) / 1000 / 60 / 60 / 24 / 30;
    }

    public int capacidadActual(){
        return this.capacidad- viandas.size();
    }
    // le llega a la heladera una solicitud de apertura del sistema
    public void agregarRegistroSolicitud(RegistroSolicitud registro) throws IOException {
        if (this.capacidadActual() == 0) {
            throw new IOException("No se pueden agregar más viandas ahora, intente más tarde");
        }else{
            Broker broker = new Broker();
            //dds2024/heladera/medrano/apertura/solicitud: (ID de tarjeta)
            broker.publish("dds/heladera/"+ this.nombre + "/apertura/solicitud", registro.getTarjeta().getIdTarjeta().toString());
           this.solicitudesApertura.add(registro);
        }
    }

    public void agregarRegistroSolicitud(RegistroSolicitud registro, Broker broker) throws IOException {
        if (this.capacidadActual() == 0) {
            throw new IOException("No se pueden agregar más viandas ahora, intente más tarde");
        }else{
            //dds2024/heladera/medrano/apertura/solicitud: (ID de tarjeta)
            broker.publish("dds/heladera/"+ this.nombre + "/aperturaSolicitud", registro.getTarjeta().getIdTarjeta().toString());
            this.solicitudesApertura.add(registro);
        }
    }

    //del broker llamo agregarApertura
    public void agregarApertura() throws IOException {
        // cuando un colaborador intenta abrir la heladera, si este puede hacerlo, entonces
        // la solicitud hecha debe actualizarse y ponerse true
    }
    public void verificarRetiroVianda(Tarjeta tarjeta){

        //necesito mirar todas las solicitudes y si es que cumple con las tres Horas

    }
}
