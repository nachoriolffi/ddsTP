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
@Table ( name = "registroApertura")
public class RegistroApertura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_RegistroApertura;

    @Column(name = "fechaApertura", columnDefinition = "Date")
    private Date fechaApertura;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn ( name = "id_tarjeta")
    private Tarjeta tarjeta;
    @Enumerated(EnumType.STRING)
    private TipoSolicitud solicitud;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "registroApertura_vianda",
            joinColumns = @JoinColumn(name = "id_RegistroApertura"),
            inverseJoinColumns = @JoinColumn(name = "id_Vianda")
    )
    private List<Vianda> viandas;

    @Column(name = "retiroVianda", columnDefinition = "BOOLEAN")
    private Boolean retiroVianda; // para la redistribucion de viandas
}
