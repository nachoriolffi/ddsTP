package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoViandas;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ViandaPersisitencia {

    @Test
    public void persistirViandas(){
        RepoColaborador repoColaborador = new RepoColaborador();
        Colaborador colaborador = new Colaborador();
        repoColaborador.agregar(colaborador);
        RepoViandas repoViandas = new RepoViandas();
        Vianda vianda = new Vianda();
        vianda.setComida("Canelones");
        vianda.setFechaDonacion(new Date());
        vianda.setFechaCaducidad(new Date(2024,12,4));
        vianda.setCalorias(145.5);
        vianda.setFueEntregada(Boolean.FALSE);
        vianda.setColaborador(colaborador);
        repoViandas.agregar(vianda);
    }
    @Test
    public void modificarVianda(){
        RepoViandas repoViandas=new RepoViandas();
        Vianda vianda= repoViandas.buscar(1L);
        vianda.setFueEntregada(Boolean.TRUE);
    }
    @Test
    public void eliminarVianda(){
        RepoViandas repoViandas=new RepoViandas();
        Vianda vianda= repoViandas.buscar(1L);
        repoViandas.eliminar(vianda);
    }
}
