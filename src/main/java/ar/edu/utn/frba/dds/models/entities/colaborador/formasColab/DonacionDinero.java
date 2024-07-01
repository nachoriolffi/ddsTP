package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;


public class DonacionDinero implements FormaDeColaboracion {

    private Date fecha;
    private Float monto;
    private Integer frecuencia;
    private Date fechaColaboracion;
    private Double multiplicador;
    @Getter
    @Setter
    private TipoColaboracion tipoColaboracion = TipoColaboracion.DINERO;


    public DonacionDinero(Integer cantidad, Date fechaColaboracion) {
        this.monto = Float.valueOf(cantidad);
        this.fecha = fechaColaboracion;
        this.multiplicador = 3.0;
    };

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return this.monto * ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero();
    }


    @Override
    public Integer getCantidadViandas() {
        return null;
    }


}
