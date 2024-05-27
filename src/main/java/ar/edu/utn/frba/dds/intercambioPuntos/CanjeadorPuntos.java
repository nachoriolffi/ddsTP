package ar.edu.utn.frba.dds.intercambioPuntos;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

import java.util.List;

public class CanjeadorPuntos {

    static CanjeadorPuntos instancia = null;

    public static CanjeadorPuntos getInstancia(){
        if (instancia == null){
            instancia = new CanjeadorPuntos();
        }
        return instancia;
    }

    public Boolean CanjearPuntos(Colaborador colaborador, List<Oferta> ofertas){
      return validarPuntos(colaborador, ofertas);
    }
    // la idea general es que l calulador de puntos de oferta sea una instancia unica que sea usada por todos que nos premita
    // calcular los puntos de una lista de todas las ofertas que alguin quiera cambia y que de esta manera podamos
    public Boolean validarPuntos(Colaborador colaborador, List<Oferta> ofertas){

        if( colaborador.puntosActualesDisponibles() >= puntosTotalesOfertas(ofertas)){
            colaborador.sumarPuntosUsados(puntosTotalesOfertas(ofertas));
            colaborador.agregarOfertasCanjeadas(ofertas);
        return true;
        }
        return false;
    }

    public Double puntosTotalesOfertas(List<Oferta> ofertas){
        return ofertas.stream().mapToDouble(Oferta::getPuntosNecesarios).sum();
    }
}
