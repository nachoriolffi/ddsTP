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
public class DistribucionVianda extends FormaDeColaboracion{

    @ManyToOne
    @JoinColumn(name = "id_heladeraOrigen",nullable = false)
    private Heladera heladeraOrigen;

    @ManyToOne
    @JoinColumn(name = "id_heladeraDestino",nullable = false)
    private Heladera heladeraDestino;

    @Column(name= "cantidadViandas", columnDefinition = "INT",nullable = false)
    private Integer cantidadViandas;

    @OneToMany
    @JoinColumn(name = "id_viandas",nullable = false)
    private List<Vianda> viandasMovidas;// por ahora es opcional

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MotivoDistribucion motivo;

    @Column(name= "fechaDistribucion", columnDefinition = "DATE",nullable = false)
    private Date fechaDistribucion;

    @Column(name= "fechaColaboracion", columnDefinition = "DATE",nullable = false)
    private Date fechaColaboracion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoColaboracion tipoColaboracion = TipoColaboracion.REDISTRIBUCION_VIANDAS;

    public DistribucionVianda(){

    }


    public DistribucionVianda(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;
    };

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDistribuidas();
    }
}
