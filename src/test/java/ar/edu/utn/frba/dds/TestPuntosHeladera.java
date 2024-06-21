package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.recomendacionPuntos.AServicioRecomendacionPuntos;
import ar.edu.utn.frba.dds.models.entities.recomendacionPuntos.servicioAPI.ServicioRecomendacionPuntos;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestPuntosHeladera {
    static List<Coordenada> coordenadas;
    AServicioRecomendacionPuntos mockAdapter;

    ServicioRecomendacionPuntos servicioRecomendacionPuntos;

    ServicioRecomendacionPuntos servicio;
    @BeforeEach
    public void Declaraciones() {
        coordenadas = new ArrayList<Coordenada>();
        coordenadas.add(new Coordenada(41.387917, 2.171923));
        coordenadas.add(new Coordenada(41.379332, 2.166667));
        coordenadas.add(new Coordenada(41.388643, 2.164550));
        coordenadas.add(new Coordenada(41.390359, 2.173611));
        coordenadas.add(new Coordenada(41.385250, 2.180333));
        coordenadas.add(new Coordenada(41.382167, 2.175833));

        mockAdapter = Mockito.mock(AServicioRecomendacionPuntos.class);

        servicioRecomendacionPuntos =  Mockito.mock(ServicioRecomendacionPuntos.class);

        //servicio = Mockito.mock(ServicioRecomendacionPuntos.class);

    }

    @Test
    public void testRecomendarPuntos() throws IOException, AssertionError {
        List<Coordenada> puntosRecomendados = servicioRecomendacionPuntos.puntosRecomendados(65.000, 12.04550, 2000);

        verify(servicioRecomendacionPuntos,times(1)).puntosRecomendados(65.000, 12.04550, 2000);
        //assert puntosRecomendados.get(0).getLongitud().equals(coordenadas.get(0).getLongitud());
        //assert puntosRecomendados.get(0).getLatitud().equals(coordenadas.get(0).getLatitud());



    }

    @Test
    public void TestColaboradorObtienePuntos() throws IOException {
        Colaborador colaborador = new Colaborador();
        List<Coordenada> puntosRecomendados = colaborador.obtenerPuntosRecomendadosParaHeladera(65.000, 12.04550, 2000);
        assert puntosRecomendados.get(0).getLongitud().equals(coordenadas.get(0).getLongitud());
        assert puntosRecomendados.get(0).getLatitud().equals(coordenadas.get(0).getLatitud());
    }
}
