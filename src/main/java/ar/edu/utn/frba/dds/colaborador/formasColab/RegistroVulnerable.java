package ar.edu.utn.frba.dds.colaborador.formasColab;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.reconocimiento.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.tarjeta.Tarjeta;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RegistroVulnerable implements FormaDeColaboracion{

    private List<Tarjeta> tarjetasDonadas;
    private Integer cantidadTarjetas;
    private Date fechaColaboracion;

    public RegistroVulnerable(List<Tarjeta> tarjetasDonadas) {
        this.tarjetasDonadas = tarjetasDonadas;

        this.fechaColaboracion = new Date();
        this.cantidadTarjetas = tarjetasDonadas.size();
    }

    public RegistroVulnerable(Integer cantidad, Date fechaColaboracion) {

        this.fechaColaboracion = fechaColaboracion;
        this.cantidadTarjetas = cantidad;
    };

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadTarjetas * ConfiguracionMultiplicador.getInstance().getMultiplicadorRegistroVulnerables();
    }
}
