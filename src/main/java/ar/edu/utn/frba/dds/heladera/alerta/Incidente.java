package ar.edu.utn.frba.dds.heladera.alerta;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Incidente {
    private Date fecha;

    private TipoAlerta tipoAlerta;

    private TipoIncidente tipoIncidente;

    private Colaborador colaborador;

    private String pathFoto;

    private String descripcion;


    public Incidente(TipoAlerta tipoAlerta) {
        this.fecha = new Date();
        this.tipoAlerta = tipoAlerta;
    }

    public Incidente() {
        this.fecha = new Date();
    }

}
