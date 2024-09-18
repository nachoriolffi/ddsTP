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

    @Column(name = "comida", columnDefinition = "VARCHAR (255)", nullable = false)
    private String comida;

    @Column(name = "fechaCaducidad", nullable = false)
    private Date fechaCaducidad;

    @Column(name = "fechaDonacion", nullable = false)
    private Date fechaDonacion;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;

    @Column(name = "calorias")
    private Double calorias;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "fueEntregada", nullable = false)
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
