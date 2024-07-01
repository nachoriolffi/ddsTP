package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

public class DistribucionVianda implements FormaDeColaboracion{

    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadViandas;
    private List<Vianda> viandasMovidas;// por ahora es opcional
    private String motivo;
    private Date fechaDistribucion;
    private Date fechaColaboracion;
    private TipoColaboracion tipoColaboracion = TipoColaboracion.REDISTRIBUCION_VIANDAS;


    public DistribucionVianda(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;
    };

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDistribuidas();
    }
}
