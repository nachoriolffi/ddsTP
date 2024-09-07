package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "distribucion_vianda")
public class DistribucionVianda implements FormaDeColaboracion{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id_DistribucionVianda;
    @ManyToOne
    @JoinColumn(name = "id_heladeraOrigen" )
    private Heladera heladeraOrigen;
    @ManyToOne
    @JoinColumn(name = "id_heladeraDestino" )
    private Heladera heladeraDestino;
    @Column(name= "cantidadViandas", columnDefinition = "INT")
    private Integer cantidadViandas;
    @OneToMany
    @JoinColumn(name = "id_viandas")
    private List<Vianda> viandasMovidas;// por ahora es opcional
    @Column(name= "motivo", columnDefinition = "TEXT")
    private String motivo;
    @Column(name= "fechaDistribucion", columnDefinition = "DATE")
    private Date fechaDistribucion;
    @Column(name= "fechaColaboracion", columnDefinition = "DATE")
    private Date fechaColaboracion;
    @Enumerated(EnumType.STRING)
    private TipoColaboracion tipoColaboracion = TipoColaboracion.REDISTRIBUCION_VIANDAS;


    public DistribucionVianda(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;
    };

    public DistribucionVianda() {

    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDistribuidas();
    }
}
