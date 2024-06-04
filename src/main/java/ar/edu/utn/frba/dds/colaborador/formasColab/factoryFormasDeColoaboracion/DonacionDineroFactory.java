package ar.edu.utn.frba.dds.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.colaborador.formasColab.FormaDeColaboracion;

import java.util.Date;

public class DonacionDineroFactory implements FormaDeColaboracionFactory{
    @Override
    public FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion) {
        return new DonacionDinero(cantidad, fechaColaboracion);
    }
}
