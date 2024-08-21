package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoTarjetas;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoTarjetas implements IRepoTarjetas {

    private static RepoTarjetas instancia = null;
    @Getter
    private List<Tarjeta> tarjetas;

    private RepoTarjetas() {
        this.tarjetas = new ArrayList<Tarjeta>();
    }

    public static RepoTarjetas getInstancia() {
        if (instancia == null) {
            instancia = new RepoTarjetas();
        }
        return instancia;
    }
    public void agregarTarjeta(Tarjeta tarjeta) {this.tarjetas.add(tarjeta);}
    public void eliminarTarjeta(Tarjeta tarjeta) {this.tarjetas.remove(tarjeta);}
    public List<Tarjeta> listarTarjetas() {return tarjetas;}

    public Tarjeta buscarTarjetaPorId(String id) {
        for (Tarjeta tarjeta : this.tarjetas) {
            if (tarjeta.getIdTarjeta().toString().equals(id)) {
                return tarjeta;
            }
        }
        return null;
    }
}
