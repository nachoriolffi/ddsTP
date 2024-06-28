package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoRegistroSolicitud;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoRegistroSolicitud implements IRepoRegistroSolicitud {

    private static RepoRegistroSolicitud instancia = null;
    @Getter
    private List<RegistroSolicitud> registrosSolicitud;

    private RepoRegistroSolicitud() {
        this.registrosSolicitud = new ArrayList<RegistroSolicitud>();
    }

    public static RepoRegistroSolicitud getInstancia() {
        if (instancia == null) {
            instancia = new RepoRegistroSolicitud();
        }
        return instancia;
    }


    @Override
    public void agregarRegistroSolicitud(RegistroSolicitud registroSolicitud) {
        this.registrosSolicitud.add(registroSolicitud);

    }

    @Override
    public void eliminarRegistroSolicitud(RegistroSolicitud registroSolicitud) {
        this.registrosSolicitud.remove(registroSolicitud);
    }

    @Override
    public List<RegistroSolicitud> traerRegistroSolicitud() {
        return this.registrosSolicitud;
    }

}
