package ar.edu.utn.frba.dds.models.entities.broker;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface AccionTopico {

    public void evaluarMensaje(String id,String messageContent);

}
