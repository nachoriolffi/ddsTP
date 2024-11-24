package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.OfertaDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.OfertaCanje;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOfertaCanjeada;

import java.util.Date;

public class OfertaService {

    RepoOferta repoOferta = RepoOferta.INSTANCE;
    RepoOfertaCanjeada repoOfertaCanjeada = RepoOfertaCanjeada.INSTANCE;
    RepoColaborador repoColaborador = RepoColaborador.INSTANCE;

    public void canjearOferta(Long idUsuario, OfertaDTO ofertaDTO) {

        Colaborador colaborador = repoColaborador.buscarPorIdUsuario(idUsuario);
        Oferta oferta = repoOferta.buscar(ofertaDTO.getId());
        if (oferta.getStockInicial() > 0 && colaborador.puntosActualesDisponibles() >= ofertaDTO.getCantidadPuntos()) {
            oferta.setStockInicial(oferta.getStockInicial() - 1);
            oferta.setStockUsado(oferta.getStockUsado() + 1);
            colaborador.sumarPuntosUsados(Double.valueOf(ofertaDTO.getCantidadPuntos()));
            OfertaCanje ofertaCanjeada = new OfertaCanje();
            ofertaCanjeada.setOferta(oferta);
            ofertaCanjeada.setColaborador(colaborador);
            ofertaCanjeada.setFechaCanje(new Date());
            ofertaCanjeada.setPuntosUsados(oferta.getPuntosNecesarios());
            colaborador.agregarOferta(ofertaCanjeada);
            repoOferta.modificar(oferta);
            repoColaborador.modificar(colaborador);
            repoOfertaCanjeada.agregar(ofertaCanjeada);
        }
    }
}
