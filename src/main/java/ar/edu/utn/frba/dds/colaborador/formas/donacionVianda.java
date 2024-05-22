package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.heladera.Vianda;

import java.util.Date;
import java.util.List;

public class donacionVianda implements FormaDeColaboracion{
    private List<Vianda> viandas;

    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;

    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
