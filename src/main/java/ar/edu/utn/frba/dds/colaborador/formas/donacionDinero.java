package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class donacionDinero implements FormaDeColaboracion{
    private Date fecha;
    private Float monto;
    private Integer frecuencia;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;

    public donacionDinero(Integer cantidad,TipoColaboracion tipoColaboracion,Date fechaColaboracion){
        this.monto = Float.valueOf(cantidad);
        this.fecha = fechaColaboracion;
        this.tipoColaboracion = tipoColaboracion;
    };

    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
