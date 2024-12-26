package ar.edu.utn.frba.dds.models.entities.distancias;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;

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

    public List<Heladera> heladerasCercanas(Heladera heladera, double rango){

        List<Heladera> heladerasCercanas = new ArrayList<>();
        List<Heladera> heladeras = RepoHeladeras.INSTANCE.buscarTodos();

        for(Heladera h : heladeras){
            if (!h.getId().equals(heladera.getId()) &&
                    instanciaCalculadorDistancias.calcularDistancia(heladera.getCoordenada(), h.getCoordenada()) < rango) {
                heladerasCercanas.add(h);
            }
        }
        return heladerasCercanas;
    }
}
