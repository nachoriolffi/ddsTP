package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOfertaCanjeada;

import java.util.Date;
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
        return validarPuntos(colaborador, (Oferta) ofertas);
    }

    // la idea general es que l calulador de puntos de oferta sea una instancia unica que sea usada por todos que nos premita
    // calcular los puntos de una lista de todas las ofertas que alguin quiera cambia y que de esta manera podamos
    public Boolean validarPuntos(Colaborador colaborador, Oferta oferta) {

        if (colaborador.puntosActualesDisponibles() >= puntosTotalesOfertas((List<OfertaCanje>) oferta)) {
            colaborador.sumarPuntosUsados(puntosTotalesOfertas((List<OfertaCanje>) oferta));
            OfertaCanje ofertaCanje = new OfertaCanje();
            ofertaCanje.setColaborador(colaborador);
            ofertaCanje.setOferta(oferta);
            ofertaCanje.setPuntosUsados(oferta.getPuntosNecesarios());
            ofertaCanje.setFechaCanje(new Date());
            colaborador.agregarOferta(ofertaCanje);
            RepoOfertaCanjeada.INSTANCE.agregar(ofertaCanje);
            RepoColaborador.INSTANCE.modificar(colaborador); // agrego esto para que cada vez que un colaborador cambie los puntos me haga la modificacion de los puntos tambien tengo al duda que sea de la capa de controller.
            return true;
        }
        return false;
    }

    public Double puntosTotalesOfertas(List<OfertaCanje> ofertas) {
        return ofertas.stream().mapToDouble(OfertaCanje::getPuntosNecesarios).sum();
    }
}
