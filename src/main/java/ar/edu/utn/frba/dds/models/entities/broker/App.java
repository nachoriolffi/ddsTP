package ar.edu.utn.frba.dds.models.entities.broker;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class App {

    public static void main(String[] args) {

        String topic        = "dds2024/heladeras/almagro/medrano";
        /*
        Tengo que suscribir a cada sensor de heladera a un topic distinto
         */
        //String content      = "Message from MqttPublishSample"; POR SI QUIERO PUBLICAR
        //int qos             = 2;
        String broker       = "tcp://broker.hivemq.com:1883";
        String clientId     = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

            System.out.println("Build our receptor");
            MyCustomMessageReceptor receptor = new MyCustomMessageReceptor();

            System.out.println("Now we subscribe to the topic");
            sampleClient.subscribe(topic, receptor);

            System.out.println("Right! We are subscribed");

            //sampleClient.disconnect(); PARA DESCONECTAR EL HILO
        } catch(MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}