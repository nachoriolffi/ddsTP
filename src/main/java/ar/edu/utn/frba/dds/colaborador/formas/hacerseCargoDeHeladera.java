package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.utils.Local;

import java.util.Date;

public class hacerseCargoDeHeladera implements FormaDeColaboracion{
    private Local local;
    private Heladera heladera;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;

    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
