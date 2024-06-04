package ar.edu.utn.frba.dds.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.colaborador.formasColab.FormaDeColaboracion;

import java.util.Date;

public class DistribucionViandaFactory implements FormaDeColaboracionFactory{
    @Override
    public FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion) {
        return new DistribucionVianda(cantidad, fechaColaboracion);
    }
}
