package ar.edu.utn.frba.dds.models.entities.heladera.suscripcion;

public interface IObservableColaborador {
    void agregarColaborador(ObserverColaborador observerColaborador);
    void eliminarColaborador (ObserverColaborador observerColaborador);
    void notificar();
}
