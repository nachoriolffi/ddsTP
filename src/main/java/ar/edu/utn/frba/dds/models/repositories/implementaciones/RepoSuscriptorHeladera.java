package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.models.entities.heladera.suscripcion.ObserverColaborador;

public class RepoSuscriptorHeladera extends RepoGenerico<ObserverColaborador> {
    public static RepoSuscriptorHeladera INSTANCE = new RepoSuscriptorHeladera();
    public RepoSuscriptorHeladera() {
        super(ObserverColaborador.class);
    }
}
