package ar.edu.utn.frba.dds.Persistencia;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class HeladeraPersistencia {

    @Test
    public void persistirHeladera(){
        RepoCoordenada repoCoordenada= new RepoCoordenada();
        Coordenada coordenada = new Coordenada(	-34.598630, -58.419962);
        repoCoordenada.agregar(coordenada);
        RepoModelo repoModelo = new RepoModelo();
        ModeloHeladera modelo = new ModeloHeladera();
        modelo.setPeso(200.0);
        modelo.setCantidadMaximaDeViandas(400);
        modelo.setTemperaturaMaxima(21.0);
        modelo.setTemperaturaMinima(3.0);
        repoModelo.agregar(modelo);
        RepoHeladeras repoHeladeras = new RepoHeladeras();
        Heladera heladera = new Heladera();
        heladera.setNombre("UTN MEDRANO");
        heladera.setCoordenada(coordenada);
        heladera.setFechaPuestaFunc(new Date());
        heladera.setEstaActiva(Boolean.TRUE);
        heladera.setModelo(modelo);
        repoHeladeras.agregar(heladera);

    }
    @Test
    public void modificarHeladera(){

        RepoColaborador repoColaborador = new RepoColaborador();
        Colaborador colaborador = new Colaborador();
        repoColaborador.agregar(colaborador);

        RepoViandas repoViandas = new RepoViandas();
        Vianda vianda = new Vianda();
        vianda.setComida("Fideos Con Salsa");
        vianda.setFechaCaducidad(new Date(2024,11,14));
        vianda.setFechaDonacion(new Date());
        vianda.setColaborador(colaborador);
        vianda.setFueEntregada(Boolean.TRUE);
        repoViandas.agregar(vianda);

        RepoHeladeras repoHeladeras = new RepoHeladeras();
        Heladera heladera =repoHeladeras.buscar(1L);
        heladera.setEstaActiva(Boolean.FALSE);
        heladera.setViandas((List<Vianda>) vianda);
        repoHeladeras.modificar(heladera);
    }

    @Test
    public void eliminarHeladedera(){
        RepoHeladeras repoHeladeras = new RepoHeladeras();
        Heladera heladera = repoHeladeras.buscar(1L);
        repoHeladeras.eliminar(heladera);
    }
    

    
}
