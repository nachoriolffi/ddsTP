package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.heladera.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class distribucionVianda implements FormaDeColaboracion{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadViandas;
    private String motivo;
    private Date fechaDistribucion;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;


    public distribucionVianda(Integer cantidad, TipoColaboracion tipoDonacion, Date fechaColaboracion) {
        this.tipoColaboracion = tipoDonacion;
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;
    };

    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
