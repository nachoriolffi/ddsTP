package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;

public class AlertaAccion implements AccionTopico {

    @Override
    public void evaluarMensaje(String id, String messageContent) {
        System.out.println("Alerta: " + messageContent);
        Heladera heladera = RepoHeladeras.INSTANCE.buscar(Long.parseLong(id));
        heladera.getReceptorMovimiento().evaluarDatosSensor(messageContent, heladera);
    }
}
