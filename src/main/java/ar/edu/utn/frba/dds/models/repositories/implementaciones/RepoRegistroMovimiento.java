package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;

public class RepoRegistroMovimiento extends RepoGenerico<ReceptorMovimiento> {

    public static RepoRegistroMovimiento INSTANCE = new RepoRegistroMovimiento();

    public RepoRegistroMovimiento() {
        super(ReceptorMovimiento.class);
    }
}
