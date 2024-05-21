package ar.edu.utn.frba.dds.vulnerable;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.heladera.Vianda;

import java.util.Date;
import java.util.List;

public class Tarjeta {
    private Integer idTarjeta;
    private List<UsoTarjeta> registroUsos;
    private Vulnerable personaAsociada;
    private Colaborador colaboradorAsociado;
    private Date fechaRegistro;

    private Vianda sacarVianda(Vianda viandaQuitada, Heladera heladera) {
        heladera.getViandas().remove(viandaQuitada);
        registroUsos.add(new UsoTarjeta(new Date(), heladera));
        return viandaQuitada;
    }
}
