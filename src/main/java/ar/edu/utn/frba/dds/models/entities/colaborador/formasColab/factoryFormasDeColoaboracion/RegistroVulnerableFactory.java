package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RegistroVulnerable;

import java.util.Date;

public class RegistroVulnerableFactory implements FormaDeColaboracionFactory{
    @Override
    public FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion) {
        return new RegistroVulnerable(cantidad, fechaColaboracion);
    }
}
