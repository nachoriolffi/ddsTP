package ar.edu.utn.frba.dds.heladera;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

import java.util.Date;

public class Vianda {
    private String comida;
    private Date fechaCaducidad;
    private Date fechaDonacion;
    private Colaborador colaborador;
    private Heladera heladera;
    private Float calorias;
    private Float peso;
    private Boolean fueEntregada;

    public Vianda(String comida, Heladera heladera, Colaborador colaborador, Boolean fueEntregada) {
        this.comida = comida;
        this.heladera = heladera;
        this.colaborador = colaborador;
        this.fueEntregada = fueEntregada;
    }
}
