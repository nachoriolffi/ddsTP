package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoTecnico;

import java.util.List;

public class RepoTecnico implements IRepoTecnico {

    private static RepoTecnico instancia = null;
    private List<Tecnico> tecnicos;

    public static RepoTecnico getInstancia() {
        if( instancia == null ) {
            instancia = new RepoTecnico();
        }
        return instancia;
    }

    public void agregarTecnico(Tecnico tecnico) {
        this.tecnicos.add(tecnico);
    }

    public void eliminarTecnico(Tecnico tecnico) {
        this.tecnicos.remove(tecnico);
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public Tecnico buscarTecnico(Tecnico tecnico) {
        return tecnicos.stream().filter(t -> t.equals(tecnico)).findFirst().orElse(null);
    }
}
