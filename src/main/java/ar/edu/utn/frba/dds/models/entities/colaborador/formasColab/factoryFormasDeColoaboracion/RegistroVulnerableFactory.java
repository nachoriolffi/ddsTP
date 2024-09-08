package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RegistroVulnerable;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistroVulnerable;

import java.util.Date;

public class RegistroVulnerableFactory implements FormaDeColaboracionFactory{
    @Override
    public FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion) {
        RegistroVulnerable donacion=  new RegistroVulnerable(cantidad, fechaColaboracion);
        RepoRegistroVulnerable.INSTANCE.agregar(donacion);
        return (FormaDeColaboracion) donacion;
    }
}
