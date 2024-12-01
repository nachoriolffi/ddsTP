package ar.edu.utn.frba.dds.runnable;


import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

public class rutinaBrokerApertura implements Runnable {
    @Override
    public void run() {
        Broker broker = new Broker();
        broker.connect("2");
        broker.subscribe("heladeras/1/aperturaRealizadas");
    }
}