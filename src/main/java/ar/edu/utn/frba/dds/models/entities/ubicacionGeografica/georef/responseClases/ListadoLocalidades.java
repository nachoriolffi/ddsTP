package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Localidad;

import java.util.List;

public class ListadoLocalidades {
    public int cantidad;
    public int inicio;
    public int total;
    public List<Localidad> localidades;
    public Parametro parametros;

    public class Parametro {
        public List<String> campos;
        public int max;
        public List<String> provincias;
        public List<String> municipios;
    }
}
