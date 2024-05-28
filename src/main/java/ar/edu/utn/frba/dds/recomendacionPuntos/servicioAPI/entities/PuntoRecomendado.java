package ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.entities;

import lombok.Getter;

// CLASE MOLDE
@Getter
public class PuntoRecomendado {
    public Coordenada punto;

    private class Coordenada{
        private Double latitud;
        private Double longitud;


    }

    public PuntoRecomendado(Coordenada punto) {
        this.punto = punto;
    }

    public Double getLongitud(){
        return punto.longitud;
    }
    public Double getLatitud(){
        return punto.latitud;
    }
    public void setLongitud(Double longitud){
        punto.longitud = longitud;
    }
    public void setLatitud(Double latitud){
        punto.latitud = latitud;
    }
}
