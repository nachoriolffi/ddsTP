package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class TemperaturaAccion implements AccionTopico {



    @Override
    public void evaluarMensaje (String id , String messageContent) {
        System.out.println("Temperatura: " + messageContent);

        Heladera heladera = RepoHeladeras.getInstancia().buscarHeladeraPorNombre(id);
        heladera.getReceptorTemperatura().evaluarTemperatura(messageContent, heladera);

    }

}