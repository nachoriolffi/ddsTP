package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ServicioRecomendacion;
import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.entities.PuntoRecomendado;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestPuntosHeladera {
    static List<Coordenada> coordenadas;

    @BeforeEach
    public void Declaraciones() {
        coordenadas = new ArrayList<Coordenada>();
        coordenadas.add(new Coordenada(41.387917, 2.171923));
        coordenadas.add(new Coordenada(41.379332, 2.166667));
        coordenadas.add(new Coordenada(41.388643, 2.164550));
        coordenadas.add(new Coordenada(41.390359, 2.173611));
        coordenadas.add(new Coordenada(41.385250, 2.180333));
        coordenadas.add(new Coordenada(41.382167, 2.175833));
    }

    @Test
    public void testRecomendarPuntos() throws IOException, AssertionError {

        ServicioRecomendacion servicioRecomendacion = ServicioRecomendacion.getInstancia();
        List<PuntoRecomendado> puntosRecomendados = servicioRecomendacion.puntosRecomendados(65.000, 12.04550, 2000);

        PuntoRecomendado puntoRecomendado1 = puntosRecomendados.get(0);
        PuntoRecomendado puntoRecomendado2 = puntosRecomendados.get(1);
        PuntoRecomendado puntoRecomendado3 = puntosRecomendados.get(2);
        PuntoRecomendado puntoRecomendado4 = puntosRecomendados.get(3);
        PuntoRecomendado puntoRecomendado5 = puntosRecomendados.get(4);
        PuntoRecomendado puntoRecomendado6 = puntosRecomendados.get(5);


        Coordenada coordenada1 = new Coordenada(puntoRecomendado1.getLatitud(), puntoRecomendado1.getLongitud());
        Coordenada coordenada2 = new Coordenada(puntoRecomendado2.getLatitud(), puntoRecomendado2.getLongitud());
        Coordenada coordenada3 = new Coordenada(puntoRecomendado3.getLatitud(), puntoRecomendado3.getLongitud());
        Coordenada coordenada4 = new Coordenada(puntoRecomendado4.getLatitud(), puntoRecomendado4.getLongitud());
        Coordenada coordenada5 = new Coordenada(puntoRecomendado5.getLatitud(), puntoRecomendado5.getLongitud());
        Coordenada coordenada6 = new Coordenada(puntoRecomendado6.getLatitud(), puntoRecomendado6.getLongitud());


        assert coordenada1.getLongitud().equals(coordenadas.get(0).getLongitud());
        assert coordenada1.getLatitud().equals(coordenadas.get(0).getLatitud());

        assert coordenada2.getLongitud().equals(coordenadas.get(1).getLongitud());
        assert coordenada2.getLatitud().equals(coordenadas.get(1).getLatitud());

        assert coordenada3.getLongitud().equals(coordenadas.get(2).getLongitud());
        assert coordenada3.getLatitud().equals(coordenadas.get(2).getLatitud());

        assert coordenada4.getLongitud().equals(coordenadas.get(3).getLongitud());
        assert coordenada4.getLatitud().equals(coordenadas.get(3).getLatitud());

        assert coordenada5.getLongitud().equals(coordenadas.get(4).getLongitud());
        assert coordenada5.getLatitud().equals(coordenadas.get(4).getLatitud());

        assert coordenada6.getLongitud().equals(coordenadas.get(5).getLongitud());
        assert coordenada6.getLatitud().equals(coordenadas.get(5).getLatitud());

    }

    @Test
    public void TestColaboradorObtienePuntos() throws IOException {
        Colaborador colaborador = new Colaborador();
        List<Coordenada> puntosRecomendados = colaborador.obtenerPuntosRecomendadosParaHeladera(65.000, 12.04550, 2000);
        Coordenada coordenada1 = puntosRecomendados.get(0);
        assert coordenada1.getLongitud().equals(coordenadas.get(0).getLongitud());
        assert coordenada1.getLatitud().equals(coordenadas.get(0).getLatitud());
    }
}
