package ar.edu.utn.frba.dds.models.entities.broker;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Broker {

    private String topic;
    private String content;
    private String broker = "tcp://broker.hivemq.com:1883";
    private  String clientId;
    IMqttClient client;

    public void connect(String clientId) {
        try {
            MemoryPersistence persistence = new MemoryPersistence();

            client = new MqttClient(broker, clientId, persistence);

            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);

            client.connect(connectOptions);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String content) {
        try {
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(1); // Garantiza que el mensaje se entrego una vez como minimo
            client.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic) {
        try {
            MyCustomMessageReceptor receptor = new MyCustomMessageReceptor();
            client.subscribe(topic, receptor);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}