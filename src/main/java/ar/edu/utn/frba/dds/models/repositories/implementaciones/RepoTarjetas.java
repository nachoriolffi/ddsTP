package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoTarjetas;
import lombok.Getter;

import java.util.List;

public class RepoTarjetas implements IRepoTarjetas {

    private static RepoTarjetas instancia = null;
    @Getter
    private List<Tarjeta> tarjetas;

    public static RepoTarjetas getInstancia() {
        if (instancia == null) {
            instancia = new RepoTarjetas();
        }
        return instancia;
    }

    @Override
    public void agregarTarjeta(Tarjeta tarjeta) {
        tarjetas.add(tarjeta);
    }

    @Override
    public void eliminarTarjeta(Tarjeta tarjeta) {
        tarjetas.remove(tarjeta);
    }

    @Override
    public List<Tarjeta> listarTarjetas() {
        return tarjetas;
    }
}
