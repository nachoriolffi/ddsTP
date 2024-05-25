package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.vulnerable.Tarjeta;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class registroVulnerable implements FormaDeColaboracion{

    private TipoColaboracion tipoColaboracion;

    private List<Tarjeta> tarjetasDonadas;

    private Integer cantidadTarjetas;

    private Date fechaColaboracion;

    public registroVulnerable(Integer cantidad, TipoColaboracion tipoDonacion, Date fechaColaboracion) {
        this.tipoColaboracion = tipoDonacion;
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadTarjetas = cantidad;
    };

    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
