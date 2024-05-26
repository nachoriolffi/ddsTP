package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ServicioRecomendacion;
import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.entities.PuntoRecomendado;
import ar.edu.utn.frba.dds.utils.Coordenada;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestPuntosHeladera {
    static List<Coordenada> coordenadas;

    @BeforeEach
    public void Declaraciones(){
        coordenadas= new ArrayList<Coordenada>();
        coordenadas.add(new Coordenada(41.387917,2.171923));
        coordenadas.add(new Coordenada(41.379332,2.166667));
        coordenadas.add(new Coordenada(41.388643,2.164550));
        coordenadas.add(new Coordenada(41.390359,2.173611));
        coordenadas.add(new Coordenada(41.385250,2.180333));
        coordenadas.add(new Coordenada(41.382167,2.175833));
    }

    @Test
    public void testRecomendarPuntos() throws IOException, AssertionError {

        ServicioRecomendacion servicioRecomendacion = ServicioRecomendacion.getInstancia();
        List<PuntoRecomendado> puntosRecomendados = servicioRecomendacion.puntosRecomendados(65.000, 12.04550, 2000);

        PuntoRecomendado puntoRecomendado1 = puntosRecomendados.get(0);
        PuntoRecomendado puntoRecomendado2 = puntosRecomendados.get(1);


        Coordenada coordenada1 = new Coordenada(puntoRecomendado1.getLatitud(), puntoRecomendado1.getLongitud());
        Coordenada coordenada2 = new Coordenada(puntoRecomendado2.getLatitud(), puntoRecomendado2.getLongitud());


        assert  coordenada1.getLongitud().equals(coordenadas.get(0).getLongitud());
        assert  coordenada1.getLatitud().equals(coordenadas.get(0).getLatitud());

        assert  coordenada2.getLongitud().equals(coordenadas.get(1).getLongitud());
        assert  coordenada2.getLatitud().equals(coordenadas.get(1).getLatitud());


    }


}
