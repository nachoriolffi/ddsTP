package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoHeladeras;
import java.util.ArrayList;
import java.util.List;

public class RepoHeladeras implements IRepoHeladeras {

    private static RepoHeladeras instancia = null;
    private List<Heladera> heladeras;

    private RepoHeladeras() {
        this.heladeras = new ArrayList<Heladera>();
    }

    public static RepoHeladeras getInstancia() {
        if (instancia == null) {
            instancia = new RepoHeladeras();
        }
        return instancia;
    }
    public void agregarHeladera(Heladera heladera) {
        this.heladeras.add(heladera);
    }
    public void eliminarHeladera(Heladera heladera) {
        this.heladeras.remove(heladera);
    }
    public List<Heladera> traerHeladeras() {
        return this.heladeras;
    }
}
