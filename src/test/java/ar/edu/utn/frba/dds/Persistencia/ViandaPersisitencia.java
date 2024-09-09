package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;

public class ViandaPersisitencia {

    @Test
    @Transactional
    public void persistirViandas() {
        // Create and persist Coordenada
        RepoCoordenada repoCoordenada = new RepoCoordenada();
        Coordenada coordenada = new Coordenada(-34.598630, -58.419962);
        repoCoordenada.agregar(coordenada);

        // Create and persist ModeloHeladera
        RepoModelo repoModelo = new RepoModelo();
        ModeloHeladera modelo = new ModeloHeladera();
        modelo.setPeso(200.0);
        modelo.setCantidadMaximaDeViandas(400);
        modelo.setTemperaturaMaxima(21.0);
        modelo.setTemperaturaMinima(3.0);
        repoModelo.agregar(modelo);

        // Create and persist Heladera
        RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
        Heladera heladera = new Heladera();
        heladera.setNombre("UTN MEDRANO");
        heladera.setCoordenada(coordenada);
        heladera.setFechaPuestaFunc(new Date());
        heladera.setEstaActiva(Boolean.TRUE);
        heladera.setModelo(modelo);
        repoHeladeras.agregar(heladera);

        // Create and persist Colaborador
        RepoColaborador repoColaborador = new RepoColaborador();
        Colaborador colaborador = new Colaborador();
        colaborador.setNombre("Juan");
        colaborador.setApellido("Gomez");
        colaborador.setNumeroDocumento(12345678);
        colaborador.setTipoDocumento(TipoDocumento.DNI);
        colaborador.setTipoPersona(TipoPersona.HUMANA);
        repoColaborador.agregar(colaborador);

        // Create and persist Vianda
        RepoViandas repoViandas = new RepoViandas();
        Vianda vianda = new Vianda();
        vianda.setComida("Canelones");
        vianda.setFechaDonacion(new Date());

        // Set fechaCaducidad using Calendar for clarity
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.DECEMBER, 4);
        vianda.setFechaCaducidad(calendar.getTime());

        vianda.setCalorias(145.5);
        vianda.setFueEntregada(Boolean.FALSE);
        vianda.setColaborador(colaborador);
        vianda.setHeladera(heladera);
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
