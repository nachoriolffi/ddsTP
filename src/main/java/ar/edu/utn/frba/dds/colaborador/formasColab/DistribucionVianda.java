package ar.edu.utn.frba.dds.colaborador.formasColab;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.reconocimiento.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.heladera.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class DistribucionVianda implements FormaDeColaboracion{

    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadViandas;
    private String motivo;
    private Date fechaDistribucion;
    private Date fechaColaboracion;


    public DistribucionVianda(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;
    };

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDistribuidas();
    }
}
