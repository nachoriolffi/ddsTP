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
public class DonacionDinero extends FormaDeColaboracion {

    @Column(name= "fecha", columnDefinition = "DATE",nullable = false)
    private Date fecha;
    @Column(name="monto", columnDefinition = "DOUBLE",nullable = false)
    private Float monto;
    @Column(name="frecuencia", columnDefinition = "INT",nullable = false)
    private Integer frecuencia; // en dias
    @Column(name="fechaColaboracion", columnDefinition = "DATE",nullable = false)
    private Date fechaColaboracion;
    @Column(name="multiplicador", columnDefinition = "DOUBLE",nullable = false)
    private Double multiplicador;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoColaboracion tipoColaboracion = TipoColaboracion.DINERO;

    public DonacionDinero(){

    }


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
