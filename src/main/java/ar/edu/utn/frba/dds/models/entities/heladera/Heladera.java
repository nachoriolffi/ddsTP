package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.suscripcion.IObservableColaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.suscripcion.ObserverColaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.suscripcion.TipoSuscripcion;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "heladera")
public class Heladera implements IObservableColaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "coordenada_id", nullable = false)
    private Coordenada coordenada;

    @Column(name = "nombre", nullable = false, columnDefinition = "VARCHAR(255)")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "direccion")
    private Direccion direccion;

    @Column(name = "viandasDisponibles")
    private Integer viandasDisponibles;

    @Column(name = "fechaPuestaFunc", nullable = false)
    private Date fechaPuestaFunc;

    @Transient // no creemos necesario persistir los receptores
    private ReceptorMovimiento receptorMovimiento;

    @Transient  // no creemos necesario persistir los receptores
    private ReceptorTemperatura receptorTemperatura;

    @OneToMany
    @JoinColumn(name = "incidente_id")
    private List<Incidente> incidentes;

    @OneToMany
    @JoinColumn(name = "solApertura_id")
    private List<RegistroSolicitud> solicitudesApertura; // es una lista de avisos a la heladera para que se abra

    @OneToMany
    @JoinColumn(name = "regApertura_id")
    private List<RegistroApertura> aperturas; // es una lista de registros de aperturas que se hicieron


    @Column(name = "activa", nullable = false)
    @Setter(AccessLevel.NONE)//pongo esto para yo definir el setter
    private Boolean estaActiva;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modelo_id", nullable = false)
    private ModeloHeladera modelo;

    @Column(name = "tempActual")
    private Double tempActual;

    @Column(name="dadaDeBaja")
    private Boolean dadaDeBaja;

    @OneToMany
    @JoinColumn(name = "heladera_id")
    private List<ObserverColaborador> observers;

    public Heladera() {
        this.incidentes = new ArrayList<>();
        this.solicitudesApertura = new ArrayList<>();
        this.aperturas = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Heladera(Direccion direccion, Coordenada coordenada) {
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.incidentes = new ArrayList<Incidente>();
        this.viandasDisponibles = 0;
        this.observers = new ArrayList<>();
    }

    public Heladera(Direccion direccion, Coordenada coordenada, Date fechaPuestaFunc) {
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.fechaPuestaFunc = fechaPuestaFunc;
        this.viandasDisponibles = 0;
        this.observers = new ArrayList<>();
    }

    public Heladera(Direccion direccion, Coordenada coordenada, Boolean estaActiva) {
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.estaActiva = estaActiva;
        this.viandasDisponibles = 0;
        this.observers = new ArrayList<>();
    }

    public void agregarVianda() {
        this.viandasDisponibles++;
        this.notificar();
    }

    public void quitarVianda() {
        this.viandasDisponibles--;
        this.notificar();
    }

    public void agregarRegistroDeAlerta(Incidente registro) {
        incidentes.add(registro);
    }

    public void configurarTemperatura(Double nuevaTemp) {
        tempActual = nuevaTemp;
    }

    public long mesesActiva(Date fechaColaboracion) {
        return (fechaColaboracion.getTime() - fechaPuestaFunc.getTime()) / 1000 / 60 / 60 / 24 / 30;
    }

    public int capacidadActual() {
        return this.modelo.getCantidadMaximaDeViandas() - this.viandasDisponibles;
    }

    public void agregarRegistroSolicitud(RegistroSolicitud registro, Broker broker) throws IOException {
        if (this.capacidadActual() == 0) {
            throw new IOException("No se pueden agregar más viandas ahora, intente más tarde");
        } else {
            //heladeras/medrano/autorizacion: (ID de tarjeta)
            broker.publish("heladeras/" + this.nombre + "/autorizacion", registro.getTarjeta().getId().toString());
            this.solicitudesApertura.add(registro);
        }
    }
    public Integer cantidadViandasLugar () {
        return this.modelo.getCantidadMaximaDeViandas() - this.viandasDisponibles;
    }

    /*----------------- BROKER -----------------*/

    // SE LLAMA A AGREGAR APERTURA

    public void agregarApertura(RegistroApertura registro) throws IOException {
        aperturas.add(registro);
        // cuando un colaborador intenta abrir la heladera, si este puede hacerlo, entonces
        // la solicitud hecha debe actualizarse y ponerse true
    }

    public void verificarRetiroVianda(Tarjeta tarjeta) {
        //necesito mirar todas las solicitudes y si es que cumple con las tres Horas
    }

    public RegistroSolicitud obtenerSolicitudApertura(int idSolicitud) {
        for (RegistroSolicitud solicitud : solicitudesApertura) {
            if (solicitud.getId().equals(idSolicitud)) {
                return solicitud;
            }
        }
        return null;
    }
    /*----------------- Suscripción a heladera -----------------*/
    @SuppressWarnings("all")// pongo esto porque me dice que puede estar definido por loombok, pero ya lo excluí
    public void setEstaActiva(Boolean activada){
        this.estaActiva = activada;
        this.notificar();
    }
    @Override
    public void agregarColaborador(ObserverColaborador observerColaborador) {
        observers.add(observerColaborador);
    }

    @Override
    public void eliminarColaborador(ObserverColaborador observerColaborador) {

    }

    @Override
    public void notificar() {
        List<ObserverColaborador> observersMuchasViandas= observers.stream()
                .filter(observerColaborador -> observerColaborador.getTipoSuscripcion() == TipoSuscripcion.MUCHAS_VIANDAS)
                .toList();

        List<ObserverColaborador> observersViandasDisponibles = observers.stream()
                .filter(observerColaborador -> observerColaborador.getTipoSuscripcion() == TipoSuscripcion.VIANDAS_DISPONIBLES)
                .toList();

        List<ObserverColaborador> observersDesperfecto = observers.stream()
                .filter(observerColaborador -> observerColaborador.getTipoSuscripcion() == TipoSuscripcion.DESPERFECTO)
                .toList();
        if(this.modelo != null)
        {
            Integer cantidadViandasLugar = this.cantidadViandasLugar(); //para cuantas viadas hay lugar en la heladera
            observersMuchasViandas.forEach(observer ->{//acaaa
                if(cantidadViandasLugar.equals(observer.getCantidadViandas()))
                {observer.getSuscriptor().recibirNotificacion("Solo queda lugar para " + observer.getCantidadViandas() + " viandas en la heladera");}
            });
        }

        observersViandasDisponibles.forEach(observer ->{
            if(Objects.equals(this.viandasDisponibles, observer.getCantidadViandas()))
            {observer.getSuscriptor().recibirNotificacion("Hay "+ observer.getCantidadViandas() + " viandas disponibles");}
        });


        if(!this.estaActiva){
            observersDesperfecto.forEach(observer ->{
                observer.getSuscriptor().recibirNotificacion("La heladera está desactivada");
            });
        }
    }
}