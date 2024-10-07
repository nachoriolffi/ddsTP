package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.usuario.Rol;

public class RepoRol extends RepoGenerico<Rol> {

    public static RepoRol INSTANCE = new RepoRol();

    public RepoRol() {
        super(Rol.class);
    }

}
