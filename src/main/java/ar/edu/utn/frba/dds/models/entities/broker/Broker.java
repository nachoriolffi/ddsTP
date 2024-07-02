package ar.edu.utn.frba.dds.models.entities.broker;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Broker {

    private String broker = "tcp://broker.hivemq.com:1883";
    IMqttClient client;

    private static Broker instancia = null;

    public static Broker getInstance(){
        if(instancia==null){
            instancia = new Broker();
        }
        return instancia;
    }

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

//USAR EL CATCH DE ACA ABAJO PARA ERRORES MAS ESPECIFICOS

/*
catch(MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
 */