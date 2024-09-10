package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoRegistroSolicitud extends RepoGenerico<RegistroSolicitud>  {

    private static RepoRegistroSolicitud instancia = null;
    @Getter
    private List<RegistroSolicitud> registrosSolicitud;

    public RepoRegistroSolicitud() {
        super(RegistroSolicitud.class);
        this.registrosSolicitud = new ArrayList<>();
    }

    public static RepoRegistroSolicitud getInstancia() {
        if (instancia == null) {
            instancia = new RepoRegistroSolicitud();
        }
        return instancia;
    }



    public void agregar(RegistroSolicitud registroSolicitud) {
        this.registrosSolicitud.add(registroSolicitud);

    }


    public void eliminar(RegistroSolicitud registroSolicitud) {
        this.registrosSolicitud.remove(registroSolicitud);
    }


    public List<RegistroSolicitud> buscar() {
        return this.registrosSolicitud;
    }


    public RegistroSolicitud buscarRegistroSolicitudPorId(int idSolicitud) {
        for (RegistroSolicitud registroSolicitud : this.registrosSolicitud) {
            if (registroSolicitud.getId_RegistroSolicitud() == idSolicitud) {
                return registroSolicitud;
            }
        }
        return null;
    }
}
