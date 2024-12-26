package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

import ar.edu.utn.frba.dds.models.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ofertaCanje")
@Getter
@Setter
public class OfertaCanje {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaCanje")
    private LocalDate fechaCanje;

    @Column(name = "puntosUsados")
    private Double puntosUsados;
}
