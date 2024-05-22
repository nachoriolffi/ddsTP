package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

import java.util.Date;

public class donacionDinero implements FormaDeColaboracion{
    private Date fecha;
    private Float monto;
    private Integer frecuencia;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;

    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
