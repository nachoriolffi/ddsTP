package ar.edu.utn.frba.dds.models.entities.heladera.suscripcion;

import ar.edu.utn.frba.dds.models.entities.colaborador.observer.IObserverColaborador;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Enabled
@Entity
@Table(name = "observer_colaborador")
public class ObserverColaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cantidadViandas")
    private Integer cantidadViandas;

    @ManyToOne
    @JoinColumn(name = "suscriptor_id")
    private IObserverColaborador suscriptor;

    @Enumerated(EnumType.STRING)
    private TipoSuscripcion tipoSuscripcion;
}
