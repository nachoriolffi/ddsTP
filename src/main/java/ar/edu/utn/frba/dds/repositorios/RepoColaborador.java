package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoColaborador {

    private static RepoColaborador instancia = null;
    @Getter private List<Colaborador> colaboradores;

    private RepoColaborador() {
        this.colaboradores=new ArrayList<Colaborador>();
    }

    public static RepoColaborador getInstancia() {
        if (instancia == null) {
            instancia = new RepoColaborador();
        }
        return instancia;
    }

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
