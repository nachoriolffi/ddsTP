package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;

public class RepoHeladeras extends RepoGenerico<Heladera> {

    public static RepoHeladeras INSTANCE = new RepoHeladeras();
    public RepoHeladeras () {
        super(Heladera.class);
    }

}
