package ar.edu.utn.frba.dds.models.entities.vianda;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

public class Vianda {
    private String comida;
    private Date fechaCaducidad;
    private Date fechaDonacion;
    private Colaborador colaborador;
    //private Heladera heladera;
    private Double calorias;
    private Double peso;
    private Boolean fueEntregada;

    public Vianda(Date fechaCaducidad, Date fechaDonacion) {
        this.fechaCaducidad = fechaCaducidad;
        this.fechaDonacion = fechaDonacion;
    }

    public Vianda(String comida,  Colaborador colaborador, Boolean fueEntregada) {
        this.comida = comida;
        this.colaborador = colaborador;
        this.fueEntregada = fueEntregada;
    }
}
