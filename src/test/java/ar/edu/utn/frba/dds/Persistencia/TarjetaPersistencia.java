package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TarjetaPersistencia {

    @Test
    public void persisirTarjeta(){
        RepoTarjeta repoTarjeta = new RepoTarjeta();
        RepoColaborador repoColaborador = new RepoColaborador();
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setFechaRegistro(new Date());
        Colaborador colaborador = new Colaborador();
        repoColaborador.agregar(colaborador);
        tarjeta.setColaboradorAsociado(colaborador);

        repoTarjeta.agregar(tarjeta);
    }
    // las tarjetas no se deberian modificar, ya que una vez creadas son para una persona sola
    // se debe testear a lo sumo que si se modifica un colaborador, cuando se traigan los datos de vuelta
    // se hallan modificados tmb en la tarjeta
    @Test
    public void eliminarTarjeta(){
        RepoTarjeta repoTarjeta = new RepoTarjeta();
        Tarjeta tarjeta = repoTarjeta.buscar(1L);
        repoTarjeta.eliminar(tarjeta);
        assert repoTarjeta.buscarTodos().isEmpty();
    }

}
