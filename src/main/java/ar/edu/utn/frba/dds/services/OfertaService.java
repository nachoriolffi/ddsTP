package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.OfertaDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;

public class OfertaService {

    RepoOferta repoOferta = RepoOferta.INSTANCE;

    public void canjearOferta(Long idUsuario, OfertaDTO ofertaDTO) {

        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(Long.valueOf(idUsuario));
        Oferta oferta = repoOferta.buscar(ofertaDTO.getId());
        if (oferta.getStockInicial() > 0 && colaborador.puntosActualesDisponibles() >= ofertaDTO.getPuntos()) {
            oferta.setStockInicial(oferta.getStockInicial() - 1);
            oferta.setStockUsado(oferta.getStockUsado() + 1);
            colaborador.sumarPuntosUsados(Double.valueOf(ofertaDTO.getPuntos()));
            colaborador.agregarOferta(oferta);
            repoOferta.modificar(oferta);
            RepoColaborador.INSTANCE.modificar(colaborador);
        }
    }
}
