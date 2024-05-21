package ar.edu.utn.frba.dds.heladera;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

import java.util.Date;

public class Vianda {
    private String comida;
    private Date fechaCaducidad;
    private Date fechaDonacion;
    private Colaborador colaborador;
    private Heladera heladera;
    private Double calorias;
    private Double peso;
    private Boolean fueEntregada;

    public Vianda(Date fechaCaducidad, Date fechaDonacion) {
        this.fechaCaducidad = fechaCaducidad;
        this.fechaDonacion = fechaDonacion;
    }

    public Vianda(String comida, Heladera heladera, Colaborador colaborador, Boolean fueEntregada) {
        this.comida = comida;
        this.heladera = heladera;
        this.colaborador = colaborador;
        this.fueEntregada = fueEntregada;
    }
}
