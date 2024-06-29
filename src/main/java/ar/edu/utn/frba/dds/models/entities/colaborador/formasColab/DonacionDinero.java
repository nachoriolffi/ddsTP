package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

@Getter
@Setter
public class DonacionDinero implements FormaDeColaboracion,Observer  {

    private Date fecha;
    private Float monto;
    private Integer frecuencia;
    private Date fechaColaboracion;
    private Double multiplicador;
    private Observable configuracionObservable;

    public DonacionDinero(Integer cantidad, Date fechaColaboracion) {
        this.monto = Float.valueOf(cantidad);
        this.fecha = fechaColaboracion;
        this.multiplicador = 3.0;
    };

    public DonacionDinero(Integer cantidad, Date fechaColaboracion,Observable configuracionObservable){
        this.monto = Float.valueOf(cantidad);
        this.fecha = fechaColaboracion;
        this.multiplicador=3.0;
        this.configuracionObservable = configuracionObservable;
        this.configuracionObservable.addObserver(this);
    };


    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return this.monto * ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero();
    }
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ConfiguracionMultiplicador) {
            ConfiguracionMultiplicador configuracion = (ConfiguracionMultiplicador) o;
            this.multiplicador = configuracion.getMultiplicadorDinero();
        }
    }

}