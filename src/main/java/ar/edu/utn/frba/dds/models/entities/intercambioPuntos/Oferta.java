package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "oferta")
@Getter
@Setter
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Oferta;
    @Column(name = "imagen", columnDefinition = "VARCHAR(250)")
    private String imagen;
    @Column(name = "nombreOferta", columnDefinition = "VARCHAR(250)",nullable = false)
    private String nombre;
    @Getter
    @Column(name = "puntosNecesarios", columnDefinition = "INT",nullable = false)
    private Integer puntosNecesarios;
    @Column(name = "fechaInicio", columnDefinition = "Date",nullable = false)
    private Date fechaInicio;
    @Column(name = "fechaFin", columnDefinition = "Date")
    private Date fechaFin;
    @Column(name = "stockInicial", columnDefinition = "INT",nullable = false)
    private Integer stockInicial;
    @Column(name = "stockUsado", columnDefinition = "Int",nullable = false)
    private Integer stockUsado;
    @Enumerated(EnumType.STRING)
    @Column(name = "rubroOferta", nullable = false)
    private Rubro rubro;

    public Oferta(String nombre, Integer puntosNecesarios, Rubro rubro, Integer stockInicial) {
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.rubro = rubro;
        this.stockInicial = stockInicial;
    }

    public Oferta(String imagen, String nombre, Integer puntosNecesarios, Rubro rubro, Integer stockInicial, Date fechaInicio, Date fechaFin) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.rubro = rubro;
        this.stockInicial = stockInicial;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Oferta() {

    }

}
