package ar.edu.utn.frba.dds.heladera.alerta;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistroDeAlerta {
    private Date fecha;

    private TipoAlerta tipoAlerta;

    public RegistroDeAlerta(TipoAlerta tipoAlerta) {
        this.fecha = new Date();
        this.tipoAlerta = tipoAlerta;
    }

    public RegistroDeAlerta() {
        this.fecha = new Date();
    }

}
