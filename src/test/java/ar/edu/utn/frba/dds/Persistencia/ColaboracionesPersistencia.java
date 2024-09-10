package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.*;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ColaboracionesPersistencia {

    @Test
    public void persistirColaboraciones() {

        // persistir colaborador
        RepoColaborador repoColaborador = new RepoColaborador();
        Colaborador colaborador = new Colaborador();
        colaborador.setTipoPersona(TipoPersona.HUMANA);
        repoColaborador.agregar(colaborador);

        // persistir donacionDinero
        RepoDonacionDinero repoDonacionDinero = new RepoDonacionDinero();
        DonacionDinero dinero = new DonacionDinero(500, new Date());
        repoDonacionDinero.agregar(dinero);
        // persistir registroVulnerable
        RepoRegistroVulnerable repoRegistroVulnerable = new RepoRegistroVulnerable();
        RegistroVulnerable registroVulnerable = new RegistroVulnerable();
        registroVulnerable.setFechaColaboracion(new Date());
        repoRegistroVulnerable.agregar(registroVulnerable);
        // persistir donacionVianda
        RepoDonacionVianda repoDonacionVianda = new RepoDonacionVianda();
        DonacionVianda donacionVianda = new DonacionVianda();
        donacionVianda.setFechaColaboracion(new Date());
        repoDonacionVianda.agregar(donacionVianda);
        // persistir distribucionVianda

        Vianda vianda = new Vianda();
        vianda.setComida("Canelones");
        vianda.setFechaDonacion(new Date());
        vianda.setHeladera(RepoHeladeras.INSTANCE.buscar(1L));
        vianda.setColaborador(RepoColaborador.INSTANCE.buscar(1L));
        vianda.setFechaCaducidad(new Date());
        vianda.setFueEntregada(Boolean.FALSE);
        RepoViandas.INSTANCE.agregar(vianda);


        RepoDistribucionVianda repoDistribucionVianda = new RepoDistribucionVianda();
        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setFechaColaboracion(new Date());
        distribucionVianda.agregarVianda(vianda);
        repoDistribucionVianda.agregar(distribucionVianda);

        RepoDistribucionVianda repoDistribucionVianda2 = new RepoDistribucionVianda();
        DistribucionVianda distribucionVianda2 = new DistribucionVianda();
        distribucionVianda2.setFechaColaboracion(new Date());
        distribucionVianda2.agregarVianda(vianda);
        repoDistribucionVianda.agregar(distribucionVianda2);

        // asignar colaboraciones a colaborador
        colaborador.agregarColaboracionRealizada(dinero);
        colaborador.agregarColaboracionRealizada(registroVulnerable);
        colaborador.agregarColaboracionRealizada(donacionVianda);
        colaborador.agregarColaboracionRealizada(distribucionVianda);
    }


}
