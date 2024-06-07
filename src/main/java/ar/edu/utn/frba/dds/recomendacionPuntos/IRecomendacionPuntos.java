package ar.edu.utn.frba.dds.recomendacionPuntos;

import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;

import java.io.IOException;
import java.util.List;

// Interfaz para el servicio interno , propia del patron adapter
public interface IRecomendacionPuntos {
    public List<Coordenada> recomendarPuntos(Double longitud, Double latitud, Integer radio) throws IOException;
}
