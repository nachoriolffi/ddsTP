package ar.edu.utn.frba.dds.models.entities.broker;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MyCustomMessageReceptor implements IMqttMessageListener {

    private final Map<String, AccionTopico> topicActions = new HashMap<>();

    public MyCustomMessageReceptor() {
        topicActions.put("temperatura", new TemperaturaAccion());
        topicActions.put("alerta", new AlertaAccion());
        topicActions.put("aperturaRealizadas", new AperturaAccion());
    }

   // private RepoHeladeras repoHeladeras = RepoHeladeras.getInstancia();
   // private List<Heladera> heladeras = repoHeladeras.traerHeladeras();

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message recived from topic " + topic + ": " + mqttMessage.toString());
        evaluarMensaje(topic, mqttMessage);
    }

    private void evaluarMensaje(String topic, MqttMessage mqttMessage) throws IOException {

        //heladeras/heladeraCasa/temperatura
        //heladeras/+/temperatura
        //heladeras/+/aperturaRealizadas
        //"heladeras/heladeraCasa/alerta"

        String[] topicEnPartes = topic.split("/");

        String Id = topicEnPartes[1];
        String subTopic = topicEnPartes[2];
        String content = new String(mqttMessage.getPayload());


        AccionTopico action = topicActions.get(subTopic);

        if (action != null) {
            action.evaluarMensaje(Id,content);
        }

       /* if(topicEnPartes[1].equals("heladera")){
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
        }*/
    }
    /*private Heladera buscarHeladeraPorNombre(String nombre) {
        for (Heladera heladera : heladeras) {
            if (heladera.getNombre().equals(nombre)) {
                return heladera;
            }
        }
        return null;
    }*/
}