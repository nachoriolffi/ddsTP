package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.heladera.Vianda;

import java.util.Date;
import java.util.List;

public class donacionVianda implements FormaDeColaboracion{
    private List<Vianda> viandas;

    private Integer cantidadViandas;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;

    public donacionVianda(Integer cantidad, TipoColaboracion tipoDonacion, Date fechaColaboracion) {
        this.tipoColaboracion = tipoDonacion;
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;

    };
    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
