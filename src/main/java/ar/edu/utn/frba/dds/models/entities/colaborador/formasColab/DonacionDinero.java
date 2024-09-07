package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;


@Entity
@Table(name="doancion_dinero")
public class DonacionDinero implements FormaDeColaboracion {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "fecha", columnDefinition = "DATE")
    private Date fecha;
    @Column(name="monto", columnDefinition = "DOUBLE")
    private Float monto;
    @Column(name="frecuencia", columnDefinition = "INT")
    private Integer frecuencia;
    @Column(name="fechaColaboracion", columnDefinition = "DATE")
    private Date fechaColaboracion;
    @Column(name="multiplicador", columnDefinition = "DOUBLE")
    private Double multiplicador;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TipoColaboracion tipoColaboracion = TipoColaboracion.DINERO;


    public DonacionDinero(Integer cantidad, Date fechaColaboracion) {
        this.monto = Float.valueOf(cantidad);
        this.fecha = fechaColaboracion;
        this.multiplicador = 3.0;
    };

    public DonacionDinero() {

    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return this.monto * ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero();
    }


    @Override
    public Integer getCantidadViandas() {
        return null;
    }


}
