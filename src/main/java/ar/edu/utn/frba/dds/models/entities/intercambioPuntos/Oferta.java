package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "oferta")
@Getter
@Setter
public class Oferta {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "pathFoto")
    private String pathFoto;

    @Column(name = "nombreOferta")
    private String nombre;

    @Column(name = "puntosNecesarios")
    private Double puntosNecesarios;

    @Column(name = "stockInicial")
    private Integer stockInicial;

    @Column(name = "stockUsado")
    private Integer stockUsado;

    @ManyToOne
    @JoinColumn(name = "rubro_id")
    private Rubro rubro;

    public Oferta() {

    }

    public Integer stockRestante() {
        return this.stockInicial - this.stockUsado;
    }

}
