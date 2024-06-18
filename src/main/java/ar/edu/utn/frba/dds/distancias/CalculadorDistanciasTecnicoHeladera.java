package ar.edu.utn.frba.dds.distancias;

import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ServicioRecomendacionPuntos;
import ar.edu.utn.frba.dds.tecnico.Tecnico;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;

import java.util.List;


public class CalculadorDistanciasTecnicoHeladera {

    private static CalculadorDistanciasTecnicoHeladera instancia = null;
    private static final CalculadorDistancias instanciaCalculadorDistancias = CalculadorDistancias.getInstance();
    public static CalculadorDistanciasTecnicoHeladera getInstance(){
        if(instancia == null){
            instancia = new CalculadorDistanciasTecnicoHeladera();
        }
        return instancia;
    }

    // tiene que devolver el tecnico mas cercano a la heladera pero no guarda cuando sale del del for al mas cercano.
    public Tecnico calcularTecnicoMasCercano(List<Tecnico> tecnicos, Heladera heladera){

        if (tecnicos.isEmpty()){
            return null;
        }

        Tecnico tecnicoMasCercano = tecnicos.get(0);
        Coordenada coordenadasHeladera = heladera.getCoordenada();

        double distancia1 = instanciaCalculadorDistancias.calcularDistancia(tecnicoMasCercano.getCoordenada(),coordenadasHeladera);
        for (Tecnico tecnico : tecnicos) {
            double distancia2 = instanciaCalculadorDistancias.calcularDistancia(tecnico.getCoordenada(), heladera.getCoordenada());
            if (distancia2 < distancia1) {
                distancia1 = distancia2;
                tecnicoMasCercano = tecnico;
            }
        }
        return tecnicoMasCercano;
    }
}
