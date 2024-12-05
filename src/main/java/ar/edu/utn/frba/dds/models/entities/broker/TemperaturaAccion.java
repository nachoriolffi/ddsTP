package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.distancias.CalculadorDistanciasTecnicoHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class TemperaturaAccion implements AccionTopico {

    @Override
    public void evaluarMensaje (String id , String messageContent) {
        System.out.println("Temperatura: " + messageContent);

        Long idHeladera = Long.parseLong(id);

        Heladera heladera = RepoHeladeras.INSTANCE.buscar(idHeladera);

        heladera.getReceptorTemperatura().evaluarTemperatura(messageContent, heladera);

    }

}
