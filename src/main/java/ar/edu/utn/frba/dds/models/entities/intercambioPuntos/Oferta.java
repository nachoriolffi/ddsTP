package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="oferta")
public class Oferta {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id_Oferta;
    @Column(name = "imagen",columnDefinition = "VARCHAR(250)")
    private String imagen;
    @Column(name = "nombreOferta",columnDefinition = "VARCHAR(250)")
    private String nombre;
    @Getter
    @Column(name = "puntosNecesarios",columnDefinition = "INT")
    private Integer puntosNecesarios;
    @Column(name = "fechaInicio",columnDefinition = "Date")
    private Date fechaInicio;
    @Column(name = "fechaFin",columnDefinition = "Date")
    private Date fechaFin;
    @Column(name = "stockInicial",columnDefinition = "INT")
    private static Integer stockInicial;
    @Column(name = "stockUsado",columnDefinition = "Date")
    private Integer stockUsado;
    @Enumerated(EnumType.STRING)
    @Column(name = "rubroOferta",nullable = false)
    private Rubro rubro;

    public Oferta(String nombre, Integer puntosNecesarios,Rubro rubro,Integer stockInicial) {
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.rubro = rubro;
        this.stockInicial = stockInicial;
    }

    public Oferta(String imagen, String nombre, Integer puntosNecesarios,Rubro rubro,Integer stockInicial, Date fechaInicio, Date fechaFin) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.rubro = rubro;
        this.stockInicial = stockInicial;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }




}
