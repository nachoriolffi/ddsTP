package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.suscripcion.ObserverColaborador;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepoSuscriptorHeladera extends RepoGenerico<ObserverColaborador> {
    public static RepoSuscriptorHeladera INSTANCE = new RepoSuscriptorHeladera();
    public RepoSuscriptorHeladera() {
        super(ObserverColaborador.class);
    }

    //quiero obtener todas las heladeras en la que aparece
    public List<Heladera> buscarHeladeras(Usuario usuario) {
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        return RepoHeladeras.INSTANCE.findBySuscriptorId(colaborador.getId());
    }

    public boolean eliminarSuscripcionPorId(Long suscripcionId) {
        ObserverColaborador suscripcion = buscar(suscripcionId);
        if (suscripcion != null) {
            eliminar(suscripcion);
            return true;
        }
        return false;
    }
}