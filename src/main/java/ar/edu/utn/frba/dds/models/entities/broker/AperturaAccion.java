package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroApertura;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistroApertura;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistroSolicitud;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AperturaAccion implements AccionTopico{

    @Override
    public void evaluarMensaje(String id, String messageContent) throws IOException {
        System.out.println("Apertura: " + messageContent);
        //"idSolicitud|idTarjeta|2024-08-14T10:00:00Z"
        String[] parts = messageContent.split("\\|");
        Long idSolicitud = Long.parseLong(parts[0]);
        Long tarjetaId = Long.parseLong(parts[1]);
        // Parse fechaApertura (ISO 8601 string) to long
        Instant instanteAux = Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(parts[2]));
        long fechaAperturaMillis = instanteAux.toEpochMilli();
        //long fechaAperturaMillis = Long.parseLong(parts[2]);
        Date fechaApertura = new Date(fechaAperturaMillis);

        Heladera heladera = RepoHeladeras.INSTANCE.buscar(Long.parseLong(id));

        RegistroSolicitud registroSolicitud = RepoRegistroSolicitud.INSTANCE.buscar(idSolicitud);

        if (registroSolicitud == null || registroSolicitud.getRealizada() ) {
            System.out.println("Solicitud no cargada o Invalida, Id: " + idSolicitud);
            return;
        }
        System.out.println("Apertura exitosa id: " + idSolicitud);
        registroSolicitud.setRealizada(true);
        RepoRegistroSolicitud.INSTANCE.modificar(registroSolicitud);

        RegistroApertura registroApertura = new RegistroApertura();
        //registroApertura.setId((long) 1L);
        registroApertura.setFechaApertura(fechaApertura);
        registroApertura.setTarjeta(registroSolicitud.getTarjeta());
        registroApertura.setSolicitud(registroSolicitud.getSolicitud());
        //registroApertura.setViandas(registroSolicitud.getCantidadViandas());
        registroApertura.setRetiroVianda(registroSolicitud.getRetiroVianda());
        registroApertura.setTarjeta(RepoTarjeta.INSTANCE.buscar(tarjetaId));
        registroApertura.setHeladera(heladera);
        RepoRegistroApertura.INSTANCE.agregar(registroApertura); // AGREGADO DE UN TODO

    }

}
