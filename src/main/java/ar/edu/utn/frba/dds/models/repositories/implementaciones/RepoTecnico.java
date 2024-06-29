package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoTecnico;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Tecnico> buscarTecnico(Long id) {
        return tecnicos
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

}
