package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

public  class CalucladorPuntos {

    private static CalucladorPuntos instancia=null;

    public static CalucladorPuntos getInstancia(){
        if (instancia == null){
            instancia = new CalucladorPuntos();
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




