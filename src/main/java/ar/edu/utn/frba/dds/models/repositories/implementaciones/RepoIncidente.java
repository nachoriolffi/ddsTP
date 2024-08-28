package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoIncidente;

import java.util.ArrayList;
import java.util.List;

public class RepoIncidente extends RepoGenerico<Incidente> implements IRepoIncidente {
    private static RepoIncidente instancia = null;
    private List<Incidente> incidentes;
    private RepoIncidente getInstancia(){
        if(instancia == null) {
            instancia = new RepoIncidente();
        }
        return instancia;
    }

    public RepoIncidente() {
        super(Incidente.class);
        this.incidentes = new ArrayList<Incidente>();
    }

    public void agregarIncidente(Incidente incidente) {
        this.incidentes.add(incidente);
    }
    public void eliminarIncidente(Incidente incidente) {
        this.incidentes.remove(incidente);
    }
    public List<Incidente> traerIncidentes() {
        return this.incidentes;
    }
    public Incidente buscarIncidente(Integer id) {return this.incidentes.get(id);}
}
