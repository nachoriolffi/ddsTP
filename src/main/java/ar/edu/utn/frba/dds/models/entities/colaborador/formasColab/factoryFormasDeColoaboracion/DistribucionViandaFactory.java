package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionDinero;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRedistribucionVianda;

import java.util.Date;

public class DistribucionViandaFactory implements FormaDeColaboracionFactory{
    @Override
    public FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion) {
        DistribucionVianda donacion = new DistribucionVianda(cantidad, fechaColaboracion);
        RepoRedistribucionVianda.INSTANCE.agregar(donacion);
        return donacion;
    }
}
