package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoTarjeta extends RepoGenerico<Tarjeta> {

    public static RepoTarjeta INSTANCE = new RepoTarjeta();

    public RepoTarjeta() {
        super(Tarjeta.class);
    }
}
