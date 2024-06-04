package ar.edu.utn.frba.dds.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.colaborador.formasColab.FormaDeColaboracion;

import java.util.Date;

public interface FormaDeColaboracionFactory {
    FormaDeColaboracion create(Integer cantidad, Date fechaColaboracion);
}
