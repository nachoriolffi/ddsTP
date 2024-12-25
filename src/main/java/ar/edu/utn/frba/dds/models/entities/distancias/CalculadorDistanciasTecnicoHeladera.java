package ar.edu.utn.frba.dds.models.entities.distancias;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;

import java.util.ArrayList;
import java.util.List;


public class CalculadorDistanciasTecnicoHeladera {

    private static final CalculadorDistancias instanciaCalculadorDistancias = CalculadorDistancias.getInstance();
    private static CalculadorDistanciasTecnicoHeladera instancia = null;

    public static CalculadorDistanciasTecnicoHeladera getInstance() {
        if (instancia == null) {
            instancia = new CalculadorDistanciasTecnicoHeladera();
        }
        return instancia;
    }

    // tiene que devolver el tecnico mas cercano a la heladera pero no guarda cuando sale del del for al mas cercano.
    public Tecnico calcularTecnicoMasCercano(List<Tecnico> tecnicos, Heladera heladera) {

        if (tecnicos.isEmpty()) {
            return null;
        }
        double distancia1 = Double.MAX_VALUE; // lo inicio con el valor mas alto, ya que asi se compara siempre
        List<Tecnico> tecnicosDisponibles = new ArrayList<>();
        Tecnico tecnicoMasCercano = null;
        Coordenada coordenadasHeladera = heladera.getCoordenada();

        // obtengo los tecnicos disponibles
        for (Tecnico tecnico : tecnicos) {
            if (tecnico.getDisponible() || tecnico.getDisponible() != null) {
                tecnicosDisponibles.add(tecnico);
            }
        }
        if (!tecnicosDisponibles.isEmpty()) {
            for (Tecnico tecnico : tecnicosDisponibles) {
                double distancia2 = instanciaCalculadorDistancias.calcularDistancia(tecnico.getCoordenada(), heladera.getCoordenada());
                if (distancia2 < distancia1) {
                    distancia1 = distancia2;
                    tecnicoMasCercano = tecnico;
                }
            }
            if (tecnicoMasCercano != null) {
                tecnicoMasCercano.setDisponible(false);
            } else {
                return null;
            }

        }

        return tecnicoMasCercano;
    }

    public List<Heladera> healderasCercanasATecnico(Usuario usuario) {

        List<Heladera> heladerasCercanas = new ArrayList<>();
        List<Heladera> heladeras = RepoHeladeras.INSTANCE.buscarTodos();

        Tecnico tecnico = RepoTecnico.INSTANCE.buscarPorUsuario(usuario.getId());
        System.out.println("TECNICO: " + tecnico.getNombre() + " " + tecnico.getApellido());

        for (Heladera heladera : heladeras) {
            if (instanciaCalculadorDistancias.calcularDistancia(tecnico.getCoordenada(), heladera.getCoordenada()) < tecnico.getAreaCobertura()) {
                System.out.println("Distancia correcta ");
                heladerasCercanas.add(heladera);
            } else {
                System.out.println("No entro ");
            }
        }
        return heladerasCercanas;


    }
}
