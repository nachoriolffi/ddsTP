package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import lombok.Data;

@Data
public class RegistroSolicitudDTO {
    private String id;
    private String fechaSolicitud;
    private String solicitud;
    private String tarjetaId;
    private String heladeraId;
    private String realizada;


    public RegistroSolicitudDTO(RegistroSolicitud registroSolicitud) {
        this.id = String.valueOf(registroSolicitud.getId());
        this.fechaSolicitud = String.valueOf(registroSolicitud.getFechaSolicitud());
        this.solicitud = String.valueOf(registroSolicitud.getSolicitud());
        this.tarjetaId = String.valueOf(registroSolicitud.getTarjeta().getId());
        this.heladeraId = String.valueOf(registroSolicitud.getHeladeraAIr().getId());
        this.realizada = String.valueOf(registroSolicitud.getRealizada());
    }


}

