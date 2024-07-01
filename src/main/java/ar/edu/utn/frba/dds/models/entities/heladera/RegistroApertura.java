package ar.edu.utn.frba.dds.models.entities.heladera;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RegistroApertura {
    private Date fechaApertura;
    private Tarjeta tarjeta;
    private TipoSolicitud solicitud;
    private List<Vianda> viandas;
    private Boolean retiroVianda; // para la redistribucion de viandas
}
