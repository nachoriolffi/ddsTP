package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;

public class RepoCuestionarioRespondido extends RepoGenerico<CuestionarioRespondido> {
    public static RepoCuestionarioRespondido INSTANCE = new RepoCuestionarioRespondido();
    public RepoCuestionarioRespondido() {
        super(CuestionarioRespondido.class);
    }
}
