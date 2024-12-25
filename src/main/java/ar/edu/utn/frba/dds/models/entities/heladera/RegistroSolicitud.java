package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "registro_solicitud")
public class RegistroSolicitud {

    @Getter
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaSolicitud")
    private Date fechaSolicitud;
    @ManyToOne
    @JoinColumn(name = "tarjeta_id")
    @Setter
    private Tarjeta tarjeta;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private TipoSolicitud solicitud; // auditoria para saber que solicitud se pidio

    @Column(name = "realizada")
    private Boolean realizada; // sirve para saber si llego o no dentro del lapso de tiempo


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "regSolicitud_de_vianda",
            joinColumns = @JoinColumn(name = "regSolicitud_id"),
            inverseJoinColumns = @JoinColumn(name = "vianda_id")
    )
    @Setter
    @Getter
    private List<Vianda> cantidadViandas;

    @Column(name = "retiroVianda")
    @Getter
    @Setter
    private Boolean retiroVianda; // sirve para saber si es la heladera que le saco o la que le meto en la redistribucion

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "heladera_id")
    private Heladera heladeraAIr;


    public RegistroSolicitud(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public RegistroSolicitud() {
        this.fechaSolicitud = new Date();
        this.cantidadViandas = new ArrayList<Vianda>();
    }


    public Tarjeta getTarjeta() {
        return tarjeta;
    }


}
