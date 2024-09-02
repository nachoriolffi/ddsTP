package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TarjetaPersistencia {

    @Test
    public void persisirTarjeta(){
        RepoTarjeta repoTarjeta = new RepoTarjeta();

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setFechaRegistro(new Date());
        Colaborador colaborador = new Colaborador();
        tarjeta.setColaboradorAsociado(colaborador);

        repoTarjeta.agregar(tarjeta);
    }
}
