package ar.edu.utn.frba.dds.recomendacionPuntos;

import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ServicioRecomendacion;
import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.entities.PuntoRecomendado;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapRecomendacionPuntos implements APIRecomendacionHeladeras {

   // Servicio que estoy adaptando
    ServicioRecomendacion servicioRecomendacion = ServicioRecomendacion.getInstancia();

    @Override
    public List<Coordenada> recomendarPuntos(Double longitud, Double latitud, Integer radio) throws IOException {
        List<Coordenada> coordenadasRecomendadas = new ArrayList<>();
        List<PuntoRecomendado> puntosRecomendados = servicioRecomendacion.puntosRecomendados(longitud, latitud, radio);
        for (PuntoRecomendado punto : puntosRecomendados) {
            coordenadasRecomendadas.add(new Coordenada(punto.getLatitud(), punto.getLongitud()));
        }
        return coordenadasRecomendadas;
    }
}
