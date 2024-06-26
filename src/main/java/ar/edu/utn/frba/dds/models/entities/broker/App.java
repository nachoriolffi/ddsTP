package ar.edu.utn.frba.dds.models.entities.broker;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class App {
    public static void main(String[] args) {
        Broker broker = new Broker();
        Broker broker2 = new Broker();
        Broker heladera = new Broker();

        broker.connect("tuki");
        heladera.connect("heladera");
        broker2.connect("alerta");

        broker.subscribe("ayudame/loco");
        broker2.subscribe("ayudame/loco/por/favor");

        heladera.publish("ayudame/loco","funciona?");
        heladera.publish("ayudame/loco/por/favor","si");

        broker.disconnect();
        broker2.disconnect();
        heladera.disconnect();

    }

}

/*
        String topic        = "ayuda/no/entiendo/nada";
        /*
        Tengo que suscribir a cada sensor de heladera a un topic distinto

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

            //sampleClient.disconnect(); //PARA DESCONECTAR EL HILO
        } catch(MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
    */
