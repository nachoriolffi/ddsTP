package ar.edu.utn.frba.dds.recomendacionPuntos;

import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ServicioRecomendacionPuntos;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;

import java.io.IOException;
import java.util.List;

public class AServicioRecomendacionPuntos implements IRecomendacionPuntos {

   // Servicio que estoy adaptando
   public ServicioRecomendacionPuntos servicioRecomendacionPuntos = ServicioRecomendacionPuntos.getInstancia();

    @Override
    public List<Coordenada> recomendarPuntos(Double longitud, Double latitud, Integer radio) throws IOException {

        return servicioRecomendacionPuntos.puntosRecomendados(longitud, latitud, radio);
    }
}
