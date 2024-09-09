package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class OfertaPersistencia {

    @Test
    public void persistirOferta() {
        RepoOferta repoOferta= new RepoOferta();
        Oferta oferta = new Oferta();
        oferta.setNombre("Televisor 45' Pindonga");
        oferta.setPuntosNecesarios(50000);
        oferta.setFechaInicio(new Date());
        oferta.setFechaFin(new Date(2024,10,24));
        oferta.setStockInicial(500);
        oferta.setStockUsado(0);
        oferta.setRubro(Rubro.TECNOLOGIA);
        repoOferta.agregar(oferta);
        assert repoOferta.buscarTodos().size()==1;
    }
    @Test
    public void modificarOferta(){
        RepoOferta repoOferta= new RepoOferta();
        Oferta oferta = repoOferta.buscar(1L);
        oferta.setPuntosNecesarios(4500);
        repoOferta.modificar(oferta);
    }
    @Test
    public void eliminarOferta(){
        RepoOferta repoOferta= new RepoOferta();
        Oferta oferta = repoOferta.buscar(Long.valueOf(1));
        repoOferta.eliminar(oferta);
        assert repoOferta.buscarTodos().size()==0;
    }

}
