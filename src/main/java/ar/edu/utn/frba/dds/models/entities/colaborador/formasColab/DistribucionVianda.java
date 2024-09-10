package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "distribucion_vianda")
public class DistribucionVianda extends FormaDeColaboracion {

    @ManyToOne
    @JoinColumn(name = "id_heladeraOrigen")
    private Heladera heladeraOrigen;

    @ManyToOne
    @JoinColumn(name = "id_heladeraDestino")
    private Heladera heladeraDestino;

    @Column(name = "cantidadViandas", columnDefinition = "INT")
    private Integer cantidadViandas;

    @ManyToMany
    @JoinTable(
            name = "viandas_movidas",
            joinColumns = @JoinColumn(name = "id_distribucion_vianda"),
            inverseJoinColumns = @JoinColumn(name = "id_vianda")
    )
    private List<Vianda> viandasMovidas;// por ahora es opcional

    @Enumerated(EnumType.STRING)
    private MotivoDistribucion motivo;

    @Column(name = "fechaDistribucion", columnDefinition = "DATE")
    private Date fechaDistribucion;

    @Column(name = "fechaColaboracion", columnDefinition = "DATE", nullable = false)
    private Date fechaColaboracion;

    @Enumerated(EnumType.STRING)
    private TipoColaboracion tipoColaboracion = TipoColaboracion.REDISTRIBUCION_VIANDAS;

    public DistribucionVianda() {
    this.viandasMovidas= new ArrayList<>();
    }

    public DistribucionVianda(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
        this.fechaDistribucion = new Date();
        this.cantidadViandas = cantidad;
    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDistribuidas();
    }

    public void agregarVianda(Vianda vianda) {
        viandasMovidas.add(vianda);
    }
}
