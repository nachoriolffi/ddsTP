package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoHeladeras;
import java.util.ArrayList;
import java.util.List;

public class RepoHeladeras extends RepoGenerico<Heladera> implements IRepoHeladeras {

    private static RepoHeladeras instancia = null;
    private List<Heladera> heladeras;

    public RepoHeladeras () {
        super(Heladera.class);
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

    public Heladera buscarHeladeraPorNombre(String nombre) {
        for (Heladera heladera : this.heladeras) {
            if (heladera.getNombre().equals(nombre)) {
                return heladera;
            }
        }
        return null;
    }
}
