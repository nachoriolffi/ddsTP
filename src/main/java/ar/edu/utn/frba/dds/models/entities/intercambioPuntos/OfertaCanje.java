package ar.edu.utn.frba.dds.models.entities.intercambioPuntos;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ofertaCanje")
@Getter
@Setter
public class OfertaCanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;

    @Column(name = "fechaCanje")
    private Date fechaCanje;

    @Column(name = "puntosUsados")
    private int puntosUsados;

    public double getPuntosNecesarios() {
        return this.oferta.getPuntosNecesarios();
    }
}
