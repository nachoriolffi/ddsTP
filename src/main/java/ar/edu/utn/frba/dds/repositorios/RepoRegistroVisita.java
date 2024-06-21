package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.tecnico.RegistroVisita;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoRegistroVisita {

    private static RepoRegistroVisita instancia = null;

   @Getter
   private final List<RegistroVisita> registrosVisita;

    private RepoRegistroVisita() {
        this.registrosVisita=new ArrayList<RegistroVisita>();
    }

    public static RepoRegistroVisita getInstancia() {
        if (instancia == null) {
            instancia = new RepoRegistroVisita();
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
}
