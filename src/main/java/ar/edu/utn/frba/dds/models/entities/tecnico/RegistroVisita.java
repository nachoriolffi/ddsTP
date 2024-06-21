package ar.edu.utn.frba.dds.models.entities.tecnico;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;

import java.util.Date;

public class RegistroVisita {

    private Date fechaVisita;
    private Tecnico tecnico;
    private String descripcion;
    private String pathFoto;
    private Incidente incidenteASolucionar;
    private Boolean problemaSolucionado;

}
