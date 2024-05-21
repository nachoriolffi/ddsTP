package ar.edu.utn.frba.dds.heladera;

import ar.edu.utn.frba.dds.utils.Coordenada;
import ar.edu.utn.frba.dds.utils.Direccion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Heladera {

    private Direccion direccion;
    private Coordenada coordenada;
    private String nombre;
    private Integer capacidad;
    private Date fechaPuestaFunc;
    private List<Vianda> viandas;
    private Sensor sensorTemp;
    private Sensor sensorMov;
    private List<RegistroDeAlerta> registrosDeAlerta;
    private Boolean estaActiva;
    private ModeloHeladera modelo;
    private Double tempActual;

    public Heladera(Direccion direccion, Coordenada coordenada, Integer capacidad, List<Vianda> viandas) {
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.capacidad = capacidad;
        this.viandas = new ArrayList<>();
    }

    public void agregarVianda(Vianda vianda) {
        viandas.add(vianda);
    }

    public void agregarRegistroDeAlerta(RegistroDeAlerta registro) {
        registrosDeAlerta.add(registro);
    }

    public void configurarTemperatura(Double nuevaTemp) {
        tempActual = nuevaTemp;
    }
}
