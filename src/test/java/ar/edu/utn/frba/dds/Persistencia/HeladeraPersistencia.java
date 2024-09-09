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

import java.util.ArrayList;
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
        RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
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

        RepoHeladeras repoHeladeras = new RepoHeladeras();
        Heladera heladera = repoHeladeras.buscar(1L);
        heladera.setEstaActiva(Boolean.FALSE);
        repoHeladeras.modificar(heladera);

    }

    @Test
    public void eliminarHeladedera(){
        RepoHeladeras repoHeladeras = new RepoHeladeras();
        Heladera heladera = repoHeladeras.buscar(1L);
        repoHeladeras.eliminar(heladera);
    }

    @Test
    public void persistirHeladeraParaServicio() {
        RepoCoordenada repoCoordenadas = RepoCoordenada.INSTANCE;
        RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
        RepoModelo repoModelos = RepoModelo.INSTANCE;

        Coordenada coord1 = new Coordenada(-34.6083, -58.3712);
        repoCoordenadas.agregar(coord1);

        ModeloHeladera modelo = new ModeloHeladera();
        //modelo.setNombre("Modelo X");
        repoModelos.agregar(modelo);

        Heladera heladera2 = new Heladera();
        heladera2.setNombre("Heladera de Fede");
        heladera2.setCoordenada(coord1);
        heladera2.setFechaPuestaFunc(new Date());
        heladera2.setEstaActiva(Boolean.TRUE);
        heladera2.setModelo(modelo);

        Heladera heladera3 = new Heladera();
        heladera3.setNombre("Heladera de Juan");
        heladera3.setCoordenada(coord1);
        heladera3.setFechaPuestaFunc(new Date());
        heladera3.setEstaActiva(Boolean.TRUE);
        heladera3.setModelo(modelo);

        repoHeladeras.agregar(heladera2);
        repoHeladeras.agregar(heladera3);
    }

    @Test
    public void agregarViandaHeladera() {


        RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
        RepoViandas repoViandas = RepoViandas.INSTANCE;
        RepoColaborador repoColaborador = RepoColaborador.INSTANCE;

        Heladera heladera = repoHeladeras.buscar(1L);
        if (heladera == null) {
            System.err.println("Heladera con id 1 no encontrada.");
            return;
        }

        Colaborador colaborador = repoColaborador.buscar(1L);
        if (colaborador == null) {
            System.err.println("Colaborador con id 1 no encontrado.");
            return;
        }

        Vianda vianda = new Vianda();
        vianda.setComida("Milanesa");
        vianda.setFechaDonacion(new Date());
        vianda.setFechaCaducidad(new Date());
        vianda.setCalorias(500.0);
        vianda.setFueEntregada(Boolean.FALSE);
        vianda.setColaborador(colaborador);
        vianda.setHeladera(heladera); // Set the foreign key relationship
        repoHeladeras.modificar(heladera);
        repoViandas.agregar(vianda);
    }


}
