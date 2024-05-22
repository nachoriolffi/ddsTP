package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.heladera.Heladera;

import java.util.Date;

public class distribucionVianda implements FormaDeColaboracion{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadViandas;
    private String motivo;
    private Date fechaDistribucion;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;


    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
