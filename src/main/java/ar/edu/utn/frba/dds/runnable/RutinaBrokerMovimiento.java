package ar.edu.utn.frba.dds.runnable;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;

public class RutinaBrokerMovimiento implements Runnable{
    @Override
    public void run() {
        Broker broker = new Broker();
        broker.connect("3");
        broker.subscribe("heladeras/+/alerta");
    }
}
