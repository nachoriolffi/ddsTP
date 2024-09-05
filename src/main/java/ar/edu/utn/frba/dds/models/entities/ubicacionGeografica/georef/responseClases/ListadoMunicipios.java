package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Municipio;

import java.util.List;

public class ListadoMunicipios {
    public int cantidad;
    public int inicio;
    public int total;
    public List<Municipio> municipios;
    public Parametro parametros;

    public class Parametro {
        public List<String> campos;
        public int max;
        public List<String> provincias;
    }
}
