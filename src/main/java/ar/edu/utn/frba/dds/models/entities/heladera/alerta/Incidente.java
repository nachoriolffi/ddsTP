package ar.edu.utn.frba.dds.models.entities.heladera.alerta;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Incidente {
    private Date fecha;

    private String descripcion;
    private String pathFoto;
    private TipoIncidente tipoIncidente;
    private TipoAlerta tipoAlerta;
    private Colaborador colaborador;

    public Incidente(TipoAlerta tipoAlerta) {
        this.fecha = new Date();
        this.tipoAlerta = tipoAlerta;
    }

    public Incidente() {
        this.fecha = new Date();
    }

    public Incidente(String descripcion, String pathFoto, TipoIncidente tipoIncidente, TipoAlerta tipoAlerta, Colaborador colaborador) {
        this.fecha = new Date();
        this.descripcion = descripcion;
        this.pathFoto = pathFoto;
        this.tipoIncidente = tipoIncidente;
        this.tipoAlerta = tipoAlerta;
        this.colaborador = colaborador;
    }

}
