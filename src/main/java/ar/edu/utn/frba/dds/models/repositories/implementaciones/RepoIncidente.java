package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;

import java.util.ArrayList;
import java.util.List;

public class RepoIncidente extends RepoGenerico<Incidente> {
    public static RepoIncidente INSTANCE = new RepoIncidente();
    public RepoIncidente() {
        super(Incidente.class);
    }
}
