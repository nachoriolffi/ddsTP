package ar.edu.utn.frba.dds.models.entities.broker;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {

    public static void main(String[] args) {
        String broker = "tcp://broker.hivemq.com:1883"; // URL del broker MQTT
        String clientId = "paho-java-client"; // Identificador único del cliente MQTT
        String topic = "dds2024/grupo25/heladera"; // Nombre del topic

        try {
            IMqttClient client = new MqttClient(broker, clientId);

            // Conectarse al broker
            client.connect();

            // Crear mensaje
            String payload = "Hello, MQTT!";
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(1); // Garantiza que el mensaje se entrego una vez como minimo

            // Publicar mensaje
            client.publish(topic, message);

            // Desconectarse del broker
            client.disconnect();

            System.out.println("Mensaje publicado con éxito!");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
