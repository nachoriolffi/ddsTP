package ar.edu.utn.frba.dds.colaborador.formasColab;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.reconocimiento.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

public class DonacionVianda implements FormaDeColaboracion {

    private List<Vianda> viandas;
    private Integer cantidadViandas;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;

    public DonacionVianda(Integer cantidad, TipoColaboracion tipoDonacion, Date fechaColaboracion) {
        this.tipoColaboracion = tipoDonacion;
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;

    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDonadas();
    }
}
