package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoHeladeras;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.util.List;


public class MyCustomMessageReceptor implements IMqttMessageListener {

    private RepoHeladeras repoHeladeras = RepoHeladeras.getInstancia();
    private List<Heladera> heladeras = repoHeladeras.traerHeladeras();

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message recived from topic " + topic + ": " + mqttMessage.toString());
        evaluarMensaje(topic, mqttMessage);
    }

    private void evaluarMensaje(String topic, MqttMessage mqttMessage) {
        //dds2024/heladera/medrano/sensor/temperatura
        //dds2024/heladera/campus/alerta

        String[] topicEnPartes = topic.split("/");

        if(topicEnPartes[1].equals("heladera")){
                String nombreHeladera = topicEnPartes[2];
                Heladera heladera = buscarHeladeraPorNombre(nombreHeladera);

            if (topicEnPartes[3].equals("alerta")){
                String mensaje = new String(mqttMessage.getPayload());
                //String mensaje = mqttMessage.toString();

                if (mensaje.equalsIgnoreCase(TipoAlerta.ROBO.toString())) {
                    ReceptorMovimiento receptorMovimiento = heladera.getReceptorMovimiento();
                    receptorMovimiento.registrarAlerta(heladera, TipoAlerta.ROBO);
                } else if (mensaje.equalsIgnoreCase(TipoAlerta.TEMPERATURA.toString())) {
                    ReceptorTemperatura receptorTemperatura = heladera.getReceptorTemperatura();
                    //receptorTemperatura.registrarIncidente(heladera,TipoAlerta.TEMPERATURA);
                    receptorTemperatura.evaluarTemperatura(heladera.getTempActual().toString(),heladera);
                }
            }
            //dds2024/heladera/medrano/apertura/solicitud: (ID de tarjeta)
            if(topicEnPartes[4].equals("solicitud")){
                try {
                    heladera.agregarApertura();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private Heladera buscarHeladeraPorNombre(String nombre) {
        for (Heladera heladera : heladeras) {
            if (heladera.getNombre().equals(nombre)) {
                return heladera;
            }
        }
        return null;
    }
}