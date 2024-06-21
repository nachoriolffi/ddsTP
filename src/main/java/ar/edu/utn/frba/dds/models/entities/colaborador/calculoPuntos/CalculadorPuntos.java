package ar.edu.utn.frba.dds.models.entities.colaborador.calculoPuntos;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;

public class CalculadorPuntos {

    private static CalculadorPuntos instancia = null;

    // uso del patron singleton
    public static CalculadorPuntos getInstancia() {
        if (instancia == null) {
            instancia = new CalculadorPuntos();
        }
        return instancia;
    }
    // sumamos puntos al colaborador de todas las colaboraciones realizadas
    public Double sumarPuntosA(Colaborador colaborador) {

        double puntos = 0.0;

        for (FormaDeColaboracion forma : colaborador.getColaboracionesRealizadas()) {
            puntos += forma.sumarPuntosA(colaborador);
        }

        //colaborador.sumarPuntos(puntos);
        return puntos;
    }


}




