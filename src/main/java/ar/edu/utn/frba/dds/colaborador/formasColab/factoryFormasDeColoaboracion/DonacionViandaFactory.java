package ar.edu.utn.frba.dds.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.colaborador.formasColab.DonacionVianda;
import ar.edu.utn.frba.dds.colaborador.formasColab.FormaDeColaboracion;

import java.util.Date;

public class DonacionViandaFactory implements FormaDeColaboracionFactory{
    @Override
    public FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion) {
        return new DonacionVianda(cantidad, fechaColaboracion);
    }
}
