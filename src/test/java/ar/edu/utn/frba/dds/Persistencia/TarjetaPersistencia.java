package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RegistroVulnerable;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistroVulnerable;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TarjetaPersistencia {

    @Test
    public void persisirTarjeta(){
        RepoRegistroVulnerable repoRegistroVulnerable = new RepoRegistroVulnerable();
        RepoTarjeta repoTarjeta = new RepoTarjeta();
        RepoColaborador repoColaborador = new RepoColaborador();
        Colaborador colaborador = new Colaborador();
        colaborador.setTipoPersona(TipoPersona.HUMANA);
        repoColaborador.agregar(colaborador);
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setFechaRegistro(new Date());
        tarjeta.setColaboradorAsociado(colaborador);
        repoTarjeta.agregar(tarjeta);
        RegistroVulnerable registroVulnerable = new RegistroVulnerable();
        registroVulnerable.setCantidadTarjetas(10);
        registroVulnerable.getTarjetasDonadas().add(tarjeta);
        registroVulnerable.setFechaColaboracion(new Date());
        registroVulnerable.setColaborador(colaborador);
        repoRegistroVulnerable.agregar(registroVulnerable);
    }
    @Test
    public void eliminarTarjeta(){
        RepoTarjeta repoTarjeta = new RepoTarjeta();
        Tarjeta tarjeta = repoTarjeta.buscar(1L);
        repoTarjeta.eliminar(tarjeta);
    }

}
