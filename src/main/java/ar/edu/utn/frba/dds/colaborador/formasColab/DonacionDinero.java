package ar.edu.utn.frba.dds.colaborador.formasColab;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.reconocimiento.config.ConfiguracionMultiplicador;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

@Getter
@Setter
public class DonacionDinero implements FormaDeColaboracion {

    private Date fecha;
    private Float monto;
    private Integer frecuencia;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;

    public DonacionDinero(Integer cantidad, TipoColaboracion tipoColaboracion, Date fechaColaboracion) {
        this.monto = Float.valueOf(cantidad);
        this.fecha = fechaColaboracion;
        this.tipoColaboracion = tipoColaboracion;
    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return this.monto * ConfiguracionMultiplicador.getInstance().getMultiplicadorDinero();
    }

}
