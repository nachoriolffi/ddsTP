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
        Broker escucha = new Broker();
        Heladera heladera = new Heladera();
        ReceptorMovimiento receptorMovimiento = new ReceptorMovimiento();
        repoHeladeras.agregarHeladera(heladera);
        heladera.setNombre("campus");

        escucha.connect("escucha");
        //escucha.subscribe("dds2024/heladera" + heladera.getId());
        escucha.subscribe("dds2024/heladera/campus/alerta");
        receptorMovimiento.evaluarDatosSensor("activado",heladera);
        escucha.disconnect();

    }
}