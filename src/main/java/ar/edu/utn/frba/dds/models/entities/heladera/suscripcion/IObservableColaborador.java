package ar.edu.utn.frba.dds.models.entities.heladera.suscripcion;

public interface IObservableColaborador {
    void agregarColaborador(ObserverColaborador observerColaborador);
    void eliminar (ObserverColaborador observerColaborador);
    void notificar();
}
