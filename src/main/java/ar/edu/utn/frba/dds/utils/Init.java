package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.entities.usuario.Rol;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.Date;

public class Init implements WithSimplePersistenceUnit {

    public static void iniciar() {
        Oferta oferta = new Oferta();
        oferta.setNombre("Televisor 45' Pindonga");
        oferta.setImagen("/img/descarga.jpg");
        oferta.setPuntosNecesarios(50000);
        oferta.setFechaInicio(new Date());
        oferta.setFechaFin(new Date(2024, 10, 24));
        oferta.setStockInicial(500);
        oferta.setStockUsado(0);
        oferta.setRubro(Rubro.TECNOLOGIA);
        RepoOferta repoOferta = RepoOferta.INSTANCE;

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

        Vianda vianda = new Vianda();
        vianda.setComida("qweqwe");
        vianda.setFechaCaducidad(new Date(2021, 10, 24));
        vianda.setFechaDonacion(new Date());
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setNombre("Pepito");
        colaborador1.setApellido("Perez");
        colaborador1.setTipoDocumento(TipoDocumento.DNI);
        colaborador1.setNumeroDocumento(32458652);
        colaborador1.setTipoPersona(TipoPersona.HUMANA);

        RepoColaborador.INSTANCE.agregar(colaborador1);
        vianda.setColaborador(colaborador1);
        vianda.setCalorias(500.0);
        vianda.setPeso(200.0);
        vianda.setFueEntregada(false);
        vianda.setHeladera(null);

        repoOferta.agregar(oferta);
        repoModelo.agregar(modelo1);
        repoModelo.agregar(modelo2);
        repoModelo.agregar(modelo3);
        RepoOferta.INSTANCE.agregar(oferta);
        RepoViandas.INSTANCE.agregar(vianda);

        /*for (TipoRol tipoRol : TipoRol.values()) {
            Rol rol = new Rol();
            rol.setTipoRol(tipoRol);
            RepoRol.INSTANCE.agregar(rol);
        }*/

    }

}
