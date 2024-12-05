package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;

import java.util.List;

public class TemperaturaAccion implements AccionTopico {

    @Override
    public void evaluarMensaje(String id, String messageContent) {
        System.out.println("Temperatura: " + messageContent);

        Long idHeladera = Long.parseLong(id);

        Heladera heladera = RepoHeladeras.INSTANCE.buscar(idHeladera);
        if (heladera.getEstaActiva()) {
            heladera.getReceptorTemperatura().evaluarTemperatura(messageContent, heladera);

            List<Incidente> incidentes = RepoIncidente.INSTANCE.getIncidentesPorHeladera(heladera.getId());
            for (Incidente incidente : incidentes) {
                if (incidente.getEstado() == false) { //osea no estan resueltos
                    incidente.notificarTecnicoMasCercano(heladera);
                }
            }
        } else {
            System.out.println("LA HELADERA YA SE ENCUENTRA INACTIVA");
        }

    }

}
