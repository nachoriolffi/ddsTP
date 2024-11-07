package ar.edu.utn.frba.dds.models.entities.colaborador.observer;

import javax.persistence.*;


public interface IObserverColaborador {
    public void recibirNotificacion(String mensaje);
}
