package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroVisita;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoRegistrosVisita;

import java.util.ArrayList;
import java.util.List;

public class RepoRegistrosVisita extends RepoGenerico<RegistroVisita> implements IRepoRegistrosVisita {

    private static RepoRegistrosVisita instancia = null;

    private final List<RegistroVisita> registrosVisita;

    public RepoRegistrosVisita () {
        super(RegistroVisita.class);
        this.registrosVisita = new ArrayList<>();
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
