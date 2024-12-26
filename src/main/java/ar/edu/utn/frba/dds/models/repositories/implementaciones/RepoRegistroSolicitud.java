package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import java.util.List;

import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import lombok.Getter;

public class RepoRegistroSolicitud extends RepoGenerico<RegistroSolicitud> {

    public static RepoRegistroSolicitud INSTANCE = new RepoRegistroSolicitud();
    @Getter
    private List<RegistroSolicitud> registrosSolicitud;

    public RepoRegistroSolicitud() {
        super(RegistroSolicitud.class);
    }


//    public RegistroSolicitud buscarRegistroSolicitudPorId(int idSolicitud) {
//        for (RegistroSolicitud registroSolicitud : this.registrosSolicitud) {
//            if (registroSolicitud.getId() == idSolicitud) {
//                return registroSolicitud;
//            }
//        }
//        return null;
//    }
}


//    @Getter
//    private List<RegistroSolicitud> registrosSolicitud;
//
//    public RepoRegistroSolicitud() {
//        super(RegistroSolicitud.class);
//        this.registrosSolicitud = new ArrayList<>();
//    }

//    public static RepoRegistroSolicitud getInstancia() {
//        if (instancia == null) {
//            instancia = new RepoRegistroSolicitud();
//        }
//        return instancia;
//    }
//
//
//
//    public void agregar(RegistroSolicitud registroSolicitud) {
//        this.registrosSolicitud.add(registroSolicitud);
//
//    }
//
//
//    public void eliminar(RegistroSolicitud registroSolicitud) {
//        this.registrosSolicitud.remove(registroSolicitud);
//    }
//
//
//    public List<RegistroSolicitud> buscar() {
//        return this.registrosSolicitud;
//    }
//
//


