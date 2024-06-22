package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoHeladeras;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoHeladeras implements IRepoHeladeras {

    private static RepoHeladeras instancia = null;
    @Getter
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

    @Override
    public void agregarHeladera(Heladera heladera) {
        this.heladeras.add(heladera);
    }

    @Override
    public void eliminarHeladera(Heladera heladera) {
        this.heladeras.remove(heladera);
    }

    @Override
    public List<Heladera> traerHeladeras() {
        return this.heladeras;
    }

    @Override
    public Heladera buscarHeladera(Heladera heladera) {
        int index = this.heladeras.indexOf(heladera);
        return this.heladeras.get(index);
    }
}
