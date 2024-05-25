package ar.edu.utn.frba.dds.colaborador.formas;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.utils.Local;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter

public class HacerseCargoDeHeladera implements FormaDeColaboracion{
    private Local local;
    private Heladera heladera;
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;
    private Integer multiplicador;

    @Override
    public void sumarPuntosA(Colaborador colaborador) {

    }
}
