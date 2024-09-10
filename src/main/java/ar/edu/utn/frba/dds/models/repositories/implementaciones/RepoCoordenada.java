package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;

public class RepoCoordenada extends RepoGenerico<Coordenada> {
  public static RepoCoordenada INSTANCE = new RepoCoordenada();

    public RepoCoordenada() {
        super(Coordenada.class);
    }
}
