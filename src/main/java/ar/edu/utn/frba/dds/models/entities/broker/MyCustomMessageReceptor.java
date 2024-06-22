package ar.edu.utn.frba.dds.models.entities.broker;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyCustomMessageReceptor implements IMqttMessageListener {

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message recived from topic " + topic + ": " + mqttMessage.toString());
        /*                                                                Recibe un String!
            Hay que pasarlo a int y aplicar logica necesaria...
         */
    }
}