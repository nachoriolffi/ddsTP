package ar.edu.utn.frba.dds.models.entities.heladera;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table ( name = "registro_apertura")
public class RegistroApertura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaApertura")
    private Date fechaApertura;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn ( name = "tarjeta_id")
    private Tarjeta tarjeta;

    @ManyToOne
    @JoinColumn(name = "heladera_id")
    private Heladera heladera;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoSolicitud")
    private TipoSolicitud solicitud;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "regApertura_de_vianda",
            joinColumns = @JoinColumn(name = "regApertura_id"),
            inverseJoinColumns = @JoinColumn(name = "vianda_id")
    )
    private List<Vianda> viandas;

    @Column(name = "retiroVianda", columnDefinition = "BOOLEAN")
    private Boolean retiroVianda; // para la redistribucion de viandas (destino u origen)
}
