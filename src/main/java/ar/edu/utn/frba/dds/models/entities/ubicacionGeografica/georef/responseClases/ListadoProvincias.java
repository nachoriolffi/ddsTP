package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Provincia;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ListadoProvincias {

    public int cantidad;
    public int inicio;
    public int total;
    public Parametro parametros;
    @Getter
    public List<Provincia> provincias;

    public ListadoProvincias() {
       this.provincias = new ArrayList<>();
    }

    public class Parametro {
        public List<String> campos;
    }


}
