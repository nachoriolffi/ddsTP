package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

public  class CalculadorPuntos {

    private static CalculadorPuntos instancia=null;

    public static CalculadorPuntos getInstancia(){
        if (instancia == null){
            instancia = new CalculadorPuntos();
        }
        return instancia;
    }

public void sumarPuntosA(Colaborador colaborador) {

    Double puntos = 0.0;

    for(FormaDeColaboracion forma :colaborador.getColaboracionesRealizadas()){
           puntos += forma.sumarPuntosA(colaborador);
    }

    colaborador.sumarPuntos(puntos);
   }




}




