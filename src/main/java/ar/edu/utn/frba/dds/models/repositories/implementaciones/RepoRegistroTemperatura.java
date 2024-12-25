package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;

public class RepoRegistroTemperatura extends RepoGenerico<RegistroTemperatura> {

    public static RepoRegistroTemperatura INSTANCE = new RepoRegistroTemperatura();

    public RepoRegistroTemperatura() {
        super(RegistroTemperatura.class);
    }


}
