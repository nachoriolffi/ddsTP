package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoViandas;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.Date;

public class Init implements WithSimplePersistenceUnit {

    public static void iniciar() {
        Oferta oferta = new Oferta();
        oferta.setNombre("Televisor 45' Pindonga");
        oferta.setImagen("../../main/resources/public/img/silla_ejemplo.jpg");
        oferta.setPuntosNecesarios(50000);
        oferta.setFechaInicio(new Date());
        oferta.setFechaFin(new Date(2024,10,24));
        oferta.setStockInicial(500);
        oferta.setStockUsado(0);
        oferta.setRubro(Rubro.TECNOLOGIA);
        //RepoOferta.INSTANCE.agregar(oferta);

        Vianda vianda = new Vianda();
        vianda.setComida("qweqwe");
        vianda.setFechaCaducidad(new Date(2021,10,24));
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
        RepoViandas.INSTANCE.agregar(vianda);
    }

}
