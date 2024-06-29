package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoColaborador;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoColaborador implements IRepoColaborador {

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


    @Override
    public java.util.Optional<Colaborador> buscar(Long id) {
        return colaboradores
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();
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
