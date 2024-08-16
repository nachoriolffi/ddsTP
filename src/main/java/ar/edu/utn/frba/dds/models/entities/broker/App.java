package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class App {
    public static void main(String[] args) {
        RepoHeladeras repoHeladeras = RepoHeladeras.getInstancia();
        Broker broker = new Broker();
        Heladera heladera = new Heladera();
        Heladera heladera2 = new Heladera();

        ReceptorMovimiento receptorMovimiento = new ReceptorMovimiento();
        ReceptorTemperatura receptorTemperatura = new ReceptorTemperatura();
        ReceptorMovimiento receptorMovimiento2 = new ReceptorMovimiento();
        ReceptorTemperatura receptorTemperatura2 = new ReceptorTemperatura();

        heladera.setReceptorMovimiento(receptorMovimiento);
        heladera.setReceptorTemperatura(receptorTemperatura);
        heladera2.setReceptorMovimiento(receptorMovimiento2);
        heladera2.setReceptorTemperatura(receptorTemperatura2);

        ModeloHeladera modelo = new ModeloHeladera( 18.0, 10.0,80.0,100 );
        heladera.setModelo(modelo);
        heladera2.setModelo(modelo);


        heladera.setNombre("campus");
        heladera2.setNombre("casa");

        repoHeladeras.agregarHeladera(heladera);
        repoHeladeras.agregarHeladera(heladera2);

        broker.connect("escucha");
        //escucha.subscribe("dds2024/heladera" + heladera.getId());
        //broker.subscribe("dds2024/heladera/campus/alerta");
        broker.subscribe("heladeras/+/temperatura");
        broker.subscribe("heladeras/+/alerta");
        //receptorMovimiento.evaluarDatosSensor("activado",heladera);

        //necesito publicar una termperatura para campus
        //broker.publish("heladeras/campus/temperatura", "30");
        //broker.publish("heladeras/casa/temperatura", "30");
        //broker.publish("heladeras/casa/temperatura", "12");
        broker.publish("heladeras/casa/alerta", "");

        //tengo q instanciar a la tarjeta
        broker.publish("heladeras/campus/","1");


        try {
            Thread.sleep(2000); // Esperar 2 segundos para asegurar la recepción
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(heladera.getIncidentes().size());
        System.out.println(heladera2.getIncidentes().size());
        broker.disconnect();

    }
}