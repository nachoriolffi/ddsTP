package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="doancion_dinero")
public class DonacionDinero extends FormaDeColaboracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "fecha", columnDefinition = "DATE")
    private Date fechaPeriodica; // esta es la periodica, que se da el valor cuando se vuelve a donar si es mensual

    @Column(name="monto", columnDefinition = "DOUBLE")
    private Float monto;

    @Column(name="frecuencia", columnDefinition = "BOOLEAN")
    private Boolean esDonacionMensual; // en dias

    @Column(name="fechaColaboracion",nullable = false,columnDefinition = "DATE")
    private Date fechaColaboracion;

    @Column(name="multiplicador", columnDefinition = "DOUBLE",nullable = false)
    private Double multiplicador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoColaboracion tipoColaboracion = TipoColaboracion.DINERO;

    public DonacionDinero(){}

    public DonacionDinero(Integer cantidad, Date fechaColaboracion) {
        this.monto = Float.valueOf(cantidad);
        this.fechaColaboracion= fechaColaboracion;
        this.fechaPeriodica = new Date();
        this.multiplicador = 3.0;
    };

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return this.monto * ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero();
    }
}
