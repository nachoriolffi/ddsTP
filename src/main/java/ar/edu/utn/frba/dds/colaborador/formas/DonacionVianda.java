package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.heladera.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter

public class DonacionVianda implements FormaDeColaboracion{
    private List<Vianda> viandas;

    private Integer cantidadViandas;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;

    public DonacionVianda(Integer cantidad, TipoColaboracion tipoDonacion, Date fechaColaboracion) {
        this.tipoColaboracion = tipoDonacion;
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;

    };
    @Override
    public double sumarPuntosA(Colaborador colaborador) {
       return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDonadas();
    }
}
