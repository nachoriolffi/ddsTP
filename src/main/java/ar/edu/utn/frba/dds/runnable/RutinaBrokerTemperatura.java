package ar.edu.utn.frba.dds.runnable;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;

public class RutinaBrokerTemperatura implements Runnable {
    @Override
    public void run() {
        Broker broker = new Broker();
        broker.connect("escuchadno");
        broker.subscribe("heladeras/+/temperatura");
    }
}
