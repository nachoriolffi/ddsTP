package ar.edu.utn.frba.dds.heladera;

import ar.edu.utn.frba.dds.utils.Coordenada;
import ar.edu.utn.frba.dds.utils.Direccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Heladera {
    private Direccion direccion;
    private Coordenada coordenada;
    private String nombre;
    private Integer capacidad;
    private Date fechaPuestaFunc;
    private List<Vianda> viandas;

    public Heladera(Direccion direccion, Coordenada coordenada, Integer capacidad, List<Vianda> viandas) {
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.capacidad = capacidad;
        this.viandas = new ArrayList<Vianda>();
    }

    public void agregarVianda(Vianda vianda){
        viandas.add(vianda);
    }

}
