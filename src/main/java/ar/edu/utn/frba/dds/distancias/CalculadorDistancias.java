package ar.edu.utn.frba.dds.distancias;

import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;

public class CalculadorDistancias {

    private static CalculadorDistancias instancia = null;

    public static CalculadorDistancias getInstance(){
        if(instancia == null){
            instancia = new CalculadorDistancias();
        }
        return instancia;
    }

    public double calcularDistancia(Coordenada coordenada1, Coordenada coordenada2) {
        // Radio de la Tierra en kilómetros
        double radioTierra = 6371;

        // Convertimos las coordenadas de grados decimales a radianes
        double lat1 = Math.toRadians(coordenada1.getLatitud());
        double lon1 = Math.toRadians(coordenada1.getLongitud());
        double lat2 = Math.toRadians(coordenada2.getLatitud());
        double lon2 = Math.toRadians(coordenada2.getLongitud());

        // Diferencia de latitud y longitud
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Fórmula de Haversine
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia entre las dos coordenadas
        double distancia = radioTierra * c;

        return distancia;
    }

}
