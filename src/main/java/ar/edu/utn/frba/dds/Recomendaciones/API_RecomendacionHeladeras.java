package ar.edu.utn.frba.dds.Recomendaciones;

import ar.edu.utn.frba.dds.utils.Coordenada;

public interface API_RecomendacionHeladeras {
    public Coordenada recomendarPuntos(Double longitud, Double altitud,Integer radio);
}
