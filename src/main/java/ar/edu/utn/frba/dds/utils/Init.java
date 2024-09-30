package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoModelo;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.Date;

public class Init implements WithSimplePersistenceUnit {

    public static void iniciar() {
        Oferta oferta = new Oferta();
        oferta.setNombre("Televisor 45' Pindonga");
        oferta.setImagen("../../main/resources/public/img/silla_ejemplo.jpg");
        oferta.setPuntosNecesarios(50000);
        oferta.setFechaInicio(new Date());
        oferta.setFechaFin(new Date(2024, 10, 24));
        oferta.setStockInicial(500);
        oferta.setStockUsado(0);
        oferta.setRubro(Rubro.TECNOLOGIA);
        RepoOferta repoOferta = RepoOferta.INSTANCE;
        //repoOferta.agregar(oferta);

        ModeloHeladera modelo1 = new ModeloHeladera();
        modelo1.setNombreModelo("Sansing X-4");
        modelo1.setPeso(145.0);
        modelo1.setCantidadMaximaDeViandas(50);
        modelo1.setTemperaturaMaxima(18.5);
        modelo1.setTemperaturaMinima(2.7);

        ModeloHeladera modelo2 = new ModeloHeladera();
        modelo2.setNombreModelo("Kingsin AB-4A");
        modelo2.setPeso(200.0);
        modelo2.setCantidadMaximaDeViandas(150);
        modelo2.setTemperaturaMaxima(20.0);
        modelo2.setTemperaturaMinima(1.5);

        ModeloHeladera modelo3 = new ModeloHeladera();
        modelo3.setNombreModelo("ChinChin 2");
        modelo3.setPeso(125.0);
        modelo3.setCantidadMaximaDeViandas(60);
        modelo3.setTemperaturaMaxima(21.3);
        modelo3.setTemperaturaMinima(4.5);

        RepoModelo repoModelo = RepoModelo.INSTANCE;
        //repoModelo.agregar(modelo1);
        //repoModelo.agregar(modelo2);
        //repoModelo.agregar(modelo3);

    }

}
