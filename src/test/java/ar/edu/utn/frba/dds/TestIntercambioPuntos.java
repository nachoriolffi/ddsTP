package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.CanjeadorPuntos;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestIntercambioPuntos
{
    Colaborador colaborador;
    List<Oferta> ofertasACanjear1 = new ArrayList<>();
    List<Oferta> ofertasACanjear2 = new ArrayList<>();
    List<Oferta> ofertasACanjear3 = new ArrayList<>();
    @BeforeEach
    public void seteoOfertas(){
        Oferta oferta1 = new Oferta("Televisor",4500.0, Rubro.TECNOLOGIA,14);
        Oferta oferta2 = new Oferta("Cortinas",500.0, Rubro.INTERIOR,100);
        Oferta oferta3 = new Oferta("Cortinas",500.0, Rubro.INTERIOR,100);
        Oferta oferta4 = new Oferta("Cama",10000.0, Rubro.HOGAR,3);
        Oferta oferta5 = new Oferta("PC Gamer",50000.0, Rubro.TECNOLOGIA,1);

        ofertasACanjear1.add(oferta1);
        ofertasACanjear1.add(oferta2);
        ofertasACanjear1.add(oferta3);
        ofertasACanjear1.add(oferta4);
        ofertasACanjear1.add(oferta5);
        ofertasACanjear2.add(oferta4);
        ofertasACanjear3.add(oferta1);


    }
    @BeforeEach
    public void seteoColaborador(){
        colaborador= new Colaborador();
    }
    @Test
    public void TestIntercambiarPuntos(){

        CanjeadorPuntos canjeadorPuntos = CanjeadorPuntos.getInstancia();
        canjeadorPuntos.CanjearPuntos(colaborador,ofertasACanjear1);
        // Hago un primer canje de productos
        assert  colaborador.getPuntosTotalesUsados().equals(65500.0);
        assert  colaborador.puntosActualesDisponibles().equals(10000.0);
        // Hago otro canje de productos
        canjeadorPuntos.CanjearPuntos(colaborador,ofertasACanjear2);
        assert  colaborador.getPuntosTotalesUsados().equals(75500.0);
        assert  colaborador.puntosActualesDisponibles().equals(0.0);
        // Hace un intento de canje y no puede cambiar los productos que quiere
        assert canjeadorPuntos.CanjearPuntos(colaborador,ofertasACanjear3).equals(Boolean.FALSE);
        assert  colaborador.puntosActualesDisponibles().equals(0.0);
        assert  colaborador.getOfertasRegistradas().size() == 6;

    }
}
