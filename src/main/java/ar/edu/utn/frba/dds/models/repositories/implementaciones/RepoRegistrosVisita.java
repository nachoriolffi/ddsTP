package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroVisita;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoRegistrosVisita;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoRegistrosVisita implements IRepoRegistrosVisita {

    private static RepoRegistrosVisita instancia = null;

    private final List<RegistroVisita> registrosVisita;

    private RepoRegistrosVisita() {
        this.registrosVisita = new ArrayList<RegistroVisita>();
    }

    public static RepoRegistrosVisita getInstancia() {
        if (instancia == null) {
            instancia = new RepoRegistrosVisita();
        }
        return instancia;
    }

    public void agregarRegistroVisita(RegistroVisita registro) {
        this.registrosVisita.add(registro);
    }

    public void eliminarRegistroVisita(RegistroVisita registro) {
        this.registrosVisita.remove(registro);
    }

    public void borrarRegistrosVisitas() {
        this.registrosVisita.clear();
    }

    public List<RegistroVisita> obtenerRegistros(){
        return this.registrosVisita;
    }
}
