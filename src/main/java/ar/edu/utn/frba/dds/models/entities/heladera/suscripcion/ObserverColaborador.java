package ar.edu.utn.frba.dds.models.entities.heladera.suscripcion;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Enabled
@Entity
@Table(name = "suscripcion")
public class ObserverColaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cantidadViandas")
    private Integer cantidadViandas;

    @ManyToOne
    @JoinColumn(name = "suscriptor_id")
    private Colaborador suscriptor; // IObserverColaborador

    @Enumerated(EnumType.STRING)
    private TipoSuscripcion tipoSuscripcion;
}