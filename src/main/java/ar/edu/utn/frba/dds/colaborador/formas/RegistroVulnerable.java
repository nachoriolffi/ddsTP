package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.vulnerable.Tarjeta;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class git RegistroVulnerable implements FormaDeColaboracion{

    private TipoColaboracion tipoColaboracion;

    private List<Tarjeta> tarjetasDonadas;

    private Integer cantidadTarjetas;

    private Date fechaColaboracion;

    public RegistroVulnerable(List<Tarjeta> tarjetasDonadas, TipoColaboracion tipoDonacion) {
        this.tarjetasDonadas = tarjetasDonadas;
        this.tipoColaboracion = tipoDonacion;
        this.fechaColaboracion = new Date();
        this.cantidadTarjetas = tarjetasDonadas.size();
    }


    public RegistroVulnerable(Integer cantidad, TipoColaboracion tipoDonacion, Date fechaColaboracion) {
        this.tipoColaboracion = tipoDonacion;
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadTarjetas = cantidad;
    };



    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadTarjetas * ConfiguracionMultiplicador.getInstance().getMultiplicadorRegistroVulnerables();
    }
}
