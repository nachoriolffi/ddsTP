package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ServicioRecomendacion;
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
        List<Coordenada> puntosRecomendados = servicioRecomendacion.puntosRecomendados(65.000, 12.04550, 2000);

        assert puntosRecomendados.get(0).getLongitud().equals(coordenadas.get(0).getLongitud());
        assert puntosRecomendados.get(0).getLatitud().equals(coordenadas.get(0).getLatitud());


    }

    @Test
    public void TestColaboradorObtienePuntos() throws IOException {
        Colaborador colaborador = new Colaborador();
        List<Coordenada> puntosRecomendados = colaborador.obtenerPuntosRecomendadosParaHeladera(65.000, 12.04550, 2000);
        assert puntosRecomendados.get(0).getLongitud().equals(coordenadas.get(0).getLongitud());
        assert puntosRecomendados.get(0).getLatitud().equals(coordenadas.get(0).getLatitud());
    }
}
