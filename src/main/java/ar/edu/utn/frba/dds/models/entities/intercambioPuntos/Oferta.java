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
    private Long id;

    @Column(name = "imagen", columnDefinition = "VARCHAR(255)")
    private String imagen;

    @Column(name = "nombreOferta", columnDefinition = "VARCHAR(255)", nullable = false)
    private String nombre;

    @Getter
    @Column(name = "puntosNecesarios", nullable = false)
    private Integer puntosNecesarios;

    @Column(name = "fechaInicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fechaFin")
    private Date fechaFin;

    @Column(name = "stockInicial", nullable = false)
    private Integer stockInicial;

    @Column(name = "stockUsado", nullable = false)
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
