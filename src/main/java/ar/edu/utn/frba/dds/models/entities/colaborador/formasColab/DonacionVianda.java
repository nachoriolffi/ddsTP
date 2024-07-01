package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;



public class DonacionVianda implements FormaDeColaboracion {

    private List<Vianda> viandas;
    @Getter
    @Setter
    private Integer cantidadViandas;
    private Date fechaColaboracion;
    @Getter
    @Setter
    private TipoColaboracion tipoColaboracion = TipoColaboracion.DONACION_VIANDAS;

    public DonacionVianda(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;

    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDonadas();
    }
}
