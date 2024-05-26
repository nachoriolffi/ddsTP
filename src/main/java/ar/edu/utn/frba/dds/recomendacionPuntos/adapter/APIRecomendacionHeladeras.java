package ar.edu.utn.frba.dds.recomendacionPuntos.adapter;

import ar.edu.utn.frba.dds.utils.Coordenada;

import java.util.List;

// Interfaz para el servicio interno , propia del patron adapter
public interface APIRecomendacionHeladeras {
    public List<Coordenada> recomendarPuntos(Double longitud,Double latitud, Integer radio);
}
