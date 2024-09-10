package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "registro_solicitud")
public class RegistroSolicitud {

    @Getter
    @Id
    @GeneratedValue ( strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id_RegistroSolicitud;

    @Column ( name = "fechaSolicitud", columnDefinition = "Date")
    private Date fechaSolicitud;
    @ManyToOne
    @JoinColumn(name = "id_tarjeta")
    @Setter
    private Tarjeta tarjeta;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private TipoSolicitud solicitud;
    @Column ( name = "realizada", columnDefinition = "BOOLEAN")
    private Boolean realizada;


    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "registro_solicitud_vianda",
            joinColumns = @JoinColumn(name = "id_RegistroSolicitud"),
            inverseJoinColumns = @JoinColumn(name = "id_Vianda")
    )
    @Setter
    @Getter
    private List<Vianda> cantidadViandas;

    @Column ( name = "retiroVianda", columnDefinition = "BOOLEAN")
    @Getter
    @Setter
    private Boolean retiroVianda;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "id_heladera")
    private Heladera heladeraAIr;


    public RegistroSolicitud(Tarjeta tarjeta){
        this.tarjeta = tarjeta;
    }

    public RegistroSolicitud(){
        this.fechaSolicitud = new Date();
        this.cantidadViandas = new ArrayList<Vianda>();
    }




    public Tarjeta getTarjeta() {
        return tarjeta;
    }


}
