package ar.edu.utn.frba.dds.intercambioPuntos;

import lombok.Getter;
import org.apache.commons.lang3.IntegerRange;

import java.util.Date;

public class Oferta {
    private String imagen;
    private String nombre;
    @Getter
    private Double puntosNecesarios;
    private Date fechaInicio;
    private Date fechaFin;
    private static Integer stockInicial;
    private Integer stockUsado;
    private Rubro rubro;

    public Oferta(String nombre, Double puntosNecesarios,Rubro rubro,Integer stockInicial) {
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.rubro = rubro;
        this.stockInicial = stockInicial;
    }

    public Oferta(String imagen, String nombre, Double puntosNecesarios,Rubro rubro,Integer stockInicial, Date fechaInicio, Date fechaFin) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.rubro = rubro;
        this.stockInicial = stockInicial;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }




}
