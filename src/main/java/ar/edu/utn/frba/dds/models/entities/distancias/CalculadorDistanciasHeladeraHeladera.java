package ar.edu.utn.frba.dds.models.entities.distancias;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;

import java.util.ArrayList;
import java.util.List;

public class CalculadorDistanciasHeladeraHeladera {
    private static CalculadorDistanciasHeladeraHeladera instancia = null;
    private static final CalculadorDistancias instanciaCalculadorDistancias = CalculadorDistancias.getInstance();

    public static CalculadorDistanciasHeladeraHeladera getInstance() {
        if (instancia == null) {
            instancia = new CalculadorDistanciasHeladeraHeladera();
        }
        return instancia;
    }

    public List<Heladera> healderasCercanas(Heladera heladera, double rango){

        List<Heladera> heladerasCercanas = new ArrayList<>();
        List<Heladera> heladeras = RepoHeladeras.INSTANCE.buscarTodos();

        for(Heladera h : heladeras){
            if(instanciaCalculadorDistancias.calcularDistancia(heladera.getCoordenada(), h.getCoordenada()) < rango){
                heladerasCercanas.add(h);

            }
        }
        return heladerasCercanas;
    }
}
