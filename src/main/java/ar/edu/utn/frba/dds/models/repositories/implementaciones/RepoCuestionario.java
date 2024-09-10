package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;

public class RepoCuestionario extends RepoGenerico<Cuestionario> {
  public static RepoCuestionario INSTANCE = new RepoCuestionario();

    public RepoCuestionario() {
        super(Cuestionario.class);
    }
}
