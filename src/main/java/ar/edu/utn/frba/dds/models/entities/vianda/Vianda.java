package ar.edu.utn.frba.dds.models.entities.vianda;

import ar.edu.utn.frba.dds.models.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vianda")
public class Vianda {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "comida")
    private String comida;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaCaducidad")
    private LocalDate fechaCaducidad;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaDonacion")
    private LocalDate fechaDonacion;

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
}
