package ar.edu.utn.frba.dds.recomendacionPuntos;

import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ServicioRecomendacion;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;

import java.io.IOException;
import java.util.List;

public class AdapRecomendacionPuntos implements APIRecomendacionHeladeras {

   // Servicio que estoy adaptando
    ServicioRecomendacion servicioRecomendacion = ServicioRecomendacion.getInstancia();

    @Override
    public List<Coordenada> recomendarPuntos(Double longitud, Double latitud, Integer radio) throws IOException {

        return servicioRecomendacion.puntosRecomendados(longitud, latitud, radio);
    }
}
