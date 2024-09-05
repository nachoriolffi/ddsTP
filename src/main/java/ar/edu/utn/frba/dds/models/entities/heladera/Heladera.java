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

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@Table (name = "heladera")
public class Heladera {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Direccion direccion;
    @OneToOne
    @JoinColumn (name = "id_Coordenada",nullable = false)
    private Coordenada coordenada;
    @Column(name = "nombre", nullable = false, columnDefinition="varchar(50)")
    private String nombre;
    @Transient // borrar la capacidad luego, ya se guarda en el modelo esta info
    private Integer capacidad;
    @Column (name = "fechaPuestaFunc", columnDefinition = "Date",nullable = false)
    private Date fechaPuestaFunc;
    @OneToMany
    @JoinColumn (name = "id_Vianda")
    private List<Vianda> viandas;
    @Transient // no creemos necesario persistir los receptores
    private ReceptorMovimiento receptorMovimiento;
    @Transient  // no creemos necesario persistir los receptores
    private ReceptorTemperatura receptorTemperatura;

    @OneToMany
    @JoinColumn(name = "id_Incidente")
    private List<Incidente> incidentes;
    @OneToMany
    @JoinColumn(name = "id_solicitudesApertura")
    private List<RegistroSolicitud>solicitudesApertura; // es una lista de avisos a la heladera para que se abra
    @OneToMany
    @JoinColumn(name = "id_RegistroApertura")
    private List<RegistroApertura> aperturas; // es una lista de registros de aperturas que se hicieron
    @Column (name = "estaActiva",nullable = false)
    private Boolean estaActiva;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="id_ModeloHeladera",nullable = false)
    private ModeloHeladera modelo;
    @Column (name = "tempActual")
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

    public void quitarVianda(Vianda vianda) {
        viandas.remove(vianda);
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


    public void agregarRegistroSolicitud(RegistroSolicitud registro, Broker broker) throws IOException {
        if (this.capacidadActual() == 0) {
            throw new IOException("No se pueden agregar más viandas ahora, intente más tarde");
        }else{
            //heladeras/medrano/autorizacion: (ID de tarjeta)
            broker.publish("heladeras/"+ this.nombre + "/autorizacion", registro.getTarjeta().getId_Tarjeta().toString());
            this.solicitudesApertura.add(registro);
        }
    }

    //del broker llamo agregarApertura
    public void agregarApertura(RegistroApertura registro) throws IOException {
        aperturas.add(registro);
        // cuando un colaborador intenta abrir la heladera, si este puede hacerlo, entonces
        // la solicitud hecha debe actualizarse y ponerse true
    }
    public void verificarRetiroVianda(Tarjeta tarjeta){

        //necesito mirar todas las solicitudes y si es que cumple con las tres Horas

    }

    public RegistroSolicitud obtenerSolicitudApertura(int idSolicitud) {
        for (RegistroSolicitud solicitud : solicitudesApertura) {
            if (solicitud.getId_RegistroSolicitud().equals(idSolicitud)) {
                return solicitud;
            }
        }
        return null;
    }
}
