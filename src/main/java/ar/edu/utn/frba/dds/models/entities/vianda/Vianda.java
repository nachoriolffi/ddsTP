package ar.edu.utn.frba.dds.models.entities.vianda;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vianda")
public class Vianda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comida", columnDefinition = "VARCHAR (255)")
    private String comida;

    @Column(name = "fechaCaducidad")
    private Date fechaCaducidad;

    @Column(name = "fechaDonacion")
    private Date fechaDonacion;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @Column(name = "calorias")
    private Double calorias;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "fueEntregada")
    private Boolean fueEntregada;

    @ManyToOne
    @JoinColumn(name = "heladera_id")
    private Heladera heladera;

    public Vianda() {

    }

    public Vianda(Date fechaCaducidad, Date fechaDonacion) {
        this.fechaCaducidad = fechaCaducidad;
        this.fechaDonacion = fechaDonacion;
    }

    public Vianda(String comida, Colaborador colaborador, Boolean fueEntregada) {
        this.comida = comida;
        this.colaborador = colaborador;
        this.fueEntregada = fueEntregada;
    }
}
