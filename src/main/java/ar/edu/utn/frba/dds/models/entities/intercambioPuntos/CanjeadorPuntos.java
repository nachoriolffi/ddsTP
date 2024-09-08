package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoGenerico;

import java.util.List;

public class CanjeadorPuntos {

    static CanjeadorPuntos instancia = null;

    public static CanjeadorPuntos getInstancia() {
        if (instancia == null) {
            instancia = new CanjeadorPuntos();
        }
        return instancia;
    }

    public Boolean CanjearPuntos(Colaborador colaborador, List<Oferta> ofertas) {
        return validarPuntos(colaborador, ofertas);
    }

    // la idea general es que l calulador de puntos de oferta sea una instancia unica que sea usada por todos que nos premita
    // calcular los puntos de una lista de todas las ofertas que alguin quiera cambia y que de esta manera podamos
    public Boolean validarPuntos(Colaborador colaborador, List<Oferta> ofertas) {

        if (colaborador.puntosActualesDisponibles() >= puntosTotalesOfertas(ofertas)) {
            colaborador.sumarPuntosUsados(puntosTotalesOfertas(ofertas));
            colaborador.agregarOfertasCanjeadas(ofertas);
            RepoColaborador.INSTANCE.modificar(colaborador); // agrego esto para que cada vez que un colaborador cambie los puntos me haga la modificacion de los puntos tambien tengo al duda que sea de la capa de controller.
            return true;
        }
        return false;
    }

    public Double puntosTotalesOfertas(List<Oferta> ofertas) {
        return ofertas.stream().mapToDouble(Oferta::getPuntosNecesarios).sum();
    }
}
