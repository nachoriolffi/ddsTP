package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.converters.DateConverter;
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
public class DonacionDinero extends FormaDeColaboracion {

    @Column(name= "fecha", columnDefinition = "DATE",nullable = false)
    private Date fecha;

    @Column(name="monto", columnDefinition = "DOUBLE")
    private Float monto;

    @Column(name="frecuencia", columnDefinition = "INT")
    private Integer frecuencia; // en dias

    @Setter
    @Column(name="fechaColaboracion")
    @Convert(converter = DateConverter.class)
    private Date fechaColaboracion;

    @Column(name="multiplicador", columnDefinition = "DOUBLE",nullable = false)
    private Double multiplicador;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoColaboracion tipoColaboracion = TipoColaboracion.DINERO;

    public DonacionDinero(){}

    public DonacionDinero(Integer cantidad, Date fechaColaboracion) {
        this.monto = Float.valueOf(cantidad);
        this.fechaColaboracion= fechaColaboracion;
        this.fecha= new Date();
        this.multiplicador = 3.0;
    };

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return this.monto * ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero();
    }
}
