package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionVianda;

import java.util.Date;

public class DonacionViandaFactory implements FormaDeColaboracionFactory{
    @Override
    public FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion) {
        DonacionVianda donacion = new DonacionVianda(cantidad, fechaColaboracion);
        RepoDonacionVianda.INSTANCE.agregar(donacion);
        return (FormaDeColaboracion) donacion ;
    }
}
