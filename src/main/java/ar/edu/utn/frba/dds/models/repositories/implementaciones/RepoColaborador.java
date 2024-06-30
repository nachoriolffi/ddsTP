package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoColaborador;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class RepoColaborador implements IRepoColaborador {

    private static RepoColaborador instancia = null;
    private List<Colaborador> colaboradores;
    private RepoColaborador() {
        this.colaboradores=new ArrayList<Colaborador>();
    }
    public static RepoColaborador getInstancia() {
        if (instancia == null) {
            instancia = new RepoColaborador();
        }
        return instancia;
    }
    public Colaborador buscar(Integer id) {return colaboradores.get(id);}
    public void agregarColaborador(Colaborador colaborador) {
        this.colaboradores.add(colaborador);
    }
    public void eliminarColaborador(Colaborador colaborador) {
        this.colaboradores.remove(colaborador);
    }
    public void borrarColaboradores() {
        this.colaboradores.clear();
    }

}
