package ar.edu.utn.frba.dds.models.repositories.interfaces;


import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;

import java.util.List;

public interface IRepoRegistroSolicitud {
    void agregarRegistroSolicitud(RegistroSolicitud registroSolicitud);
    void eliminarRegistroSolicitud(RegistroSolicitud registroSolicitud);
    List<RegistroSolicitud> traerRegistroSolicitud();
}
