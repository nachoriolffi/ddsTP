package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoHeladeras;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;


public class MyCustomMessageReceptor implements IMqttMessageListener {

    private RepoHeladeras repoHeladeras = RepoHeladeras.getInstancia();
    private List<Heladera> heladeras = repoHeladeras.traerHeladeras();

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message recived from topic " + topic + ": " + mqttMessage.toString());
        evaluarMensaje(topic, mqttMessage);
        /*                                                                Recibe un String!
            Hay que pasarlo a int y aplicar logica necesaria...
         */
    }

    private void evaluarMensaje(String topic, MqttMessage mqttMessage) {
        //dds2024/heladera/medrano/sensor/temperatura
        //dds2024/heladera/campus/alerta
        String[] topicEnPartes = topic.split("/");
        if (/*topicEnPartes[1].equals("heladera") &&*/ topicEnPartes[3].equals("alerta")){
            String nombreHeladera = topicEnPartes[2];
            System.out.println(nombreHeladera);
            Heladera heladera = buscarHeladeraPorNombre(nombreHeladera); //estoy agarrando un null??
            System.out.println(heladera.getNombre());
            String mensaje = new String(mqttMessage.getPayload());
            //String mensaje = mqttMessage.toString();

            if (mensaje.equalsIgnoreCase(TipoAlerta.ROBO.toString())) {
                ReceptorMovimiento receptorMovimiento = heladera.getReceptorMovimiento();
                receptorMovimiento.registrarAlerta(heladera, TipoAlerta.ROBO);
            } else if (mensaje.equalsIgnoreCase(TipoAlerta.TEMPERATURA.toString())) {
                ReceptorTemperatura receptorTemperatura = heladera.getReceptorTemperatura();
                //receptorTemperatura.registrarIncidente(heladera,TipoAlerta.TEMPERATURA);
                // NO VA ESTE, USAR EVALUAR TEMPERATURA
            }
        }
        //dds2024/heladera/campus/autorizacion/aceptada
        //dds2024/heladera/campus/autorizacion/denegada
        if(topicEnPartes[3].equals("autorizacion")){

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