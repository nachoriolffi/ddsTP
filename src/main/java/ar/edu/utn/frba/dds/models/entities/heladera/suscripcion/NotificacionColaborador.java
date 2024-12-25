package ar.edu.utn.frba.dds.models.entities.heladera.suscripcion;

import ar.edu.utn.frba.dds.models.entities.colaborador.observer.IObserverColaborador;

import java.util.Date;

public class NotificacionColaborador {
    private Date fechaNotificacion;
    private IObserverColaborador suscriptor;
    private String mensaje;

    public NotificacionColaborador(Date fechaNotificacion, IObserverColaborador suscriptor, String mensaje) {
        this.fechaNotificacion = fechaNotificacion;
        this.suscriptor = suscriptor;
        this.mensaje = mensaje;
    }
}
