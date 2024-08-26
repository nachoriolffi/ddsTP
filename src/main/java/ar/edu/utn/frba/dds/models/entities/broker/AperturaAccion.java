package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroApertura;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistroSolicitud;

import java.io.IOException;
import java.util.Date;

public class AperturaAccion implements AccionTopico{

    @Override
    public void evaluarMensaje(String id, String messageContent) throws IOException {
        System.out.println("Apertura: " + messageContent);
        //"idSolicitud|idTarjeta|2024-08-14T10:00:00Z"
        String[] parts = messageContent.split("\\|");
        int idSolicitud = Integer.parseInt(parts[0]);
        int tarjetaId = Integer.parseInt(parts[1]);
        long fechaAperturaMillis = Long.parseLong(parts[2]);
        Date fechaApertura = new Date(fechaAperturaMillis);

        Heladera heladera = RepoHeladeras.getInstancia().buscarHeladeraPorNombre(id);

        RegistroSolicitud registroSolicitud = RepoRegistroSolicitud.getInstancia().buscarRegistroSolicitudPorId(idSolicitud);

        if (registroSolicitud == null) {
            System.out.println("No se encontro la solicitud con id: " + idSolicitud);
            return;
        }
        RegistroApertura registroApertura = new RegistroApertura();
        registroApertura.setFechaApertura(fechaApertura);
        registroApertura.setTarjeta(registroSolicitud.getTarjeta());
        registroApertura.setSolicitud(registroSolicitud.getSolicitud());
        registroApertura.setViandas(registroSolicitud.getCantidadViandas());
        registroApertura.setRetiroVianda(registroSolicitud.getRetiroVianda());
        heladera.agregarApertura(registroApertura);
        //TODO agregar el repo de registro de apertura
    }

}
