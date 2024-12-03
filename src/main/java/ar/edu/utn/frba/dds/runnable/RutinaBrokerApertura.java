package ar.edu.utn.frba.dds.runnable;


import ar.edu.utn.frba.dds.models.entities.broker.Broker;

public class RutinaBrokerApertura implements Runnable {
    @Override
    public void run() {
        Broker broker = new Broker();
        broker.connect("2");
        broker.subscribe("heladeras/+/aperturaRealizadas");
    }
}