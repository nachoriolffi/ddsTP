package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionDinero;

import java.util.Date;

public class DonacionDineroFactory implements FormaDeColaboracionFactory{
    @Override
    public FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion) {
        DonacionDinero donacion = new DonacionDinero(cantidad, fechaColaboracion);
        RepoDonacionDinero.INSTANCE.agregar(donacion);
        return donacion;
    }
}
