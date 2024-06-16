package ar.edu.utn.frba.dds.distancias;

import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ServicioRecomendacionPuntos;
import ar.edu.utn.frba.dds.tecnico.Tecnico;

import java.util.List;


public class CalculadorDistanciasTecnicoHeladera {

    private static CalculadorDistanciasTecnicoHeladera instancia = null;

    public static CalculadorDistanciasTecnicoHeladera getInstance(){
        if(instancia == null){
            instancia = new CalculadorDistanciasTecnicoHeladera();
        }
        return instancia;
    }


    public Tecnico calcularTecnicoMasCercano(List<Tecnico> tecnicos, Heladera heladera){
        for (int i = 0;i<tecnicos.size();i++){

        }
    }
}
