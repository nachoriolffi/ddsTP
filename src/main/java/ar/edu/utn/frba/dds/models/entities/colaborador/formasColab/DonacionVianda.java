package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="donacion_vianda")
public class DonacionVianda extends FormaDeColaboracion {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    @OneToOne
    @JoinColumn(name = "id_Vianda")
    private Vianda vianda;
    @Column(name ="fechaColaboracion", columnDefinition = "DATE",nullable = false)
    private Date fechaColaboracion;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TipoColaboracion tipoColaboracion;

    public DonacionVianda(){

    }

    public DonacionVianda(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
    }


    public DonacionVianda(Vianda vianda, Date fechaColaboracion) {
        this.vianda = vianda;
        this.fechaColaboracion = fechaColaboracion;
        this.tipoColaboracion  = TipoColaboracion.DONACION_VIANDAS;
    }


    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDonadas();
    }

    public void solicitarAutorizacion(Heladera heladera, Tarjeta tarjeta){
        Broker broker = Broker.getInstance();
        broker.publish("heladeras/"+ heladera.getNombre() + "/autorizacion", tarjeta.getId_Tarjeta().toString());
    }

}
