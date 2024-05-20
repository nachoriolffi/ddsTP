package ar.edu.utn.frba.dds.heladera;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistroAlerta {
    private  Date fecha;

    private TipoAlerta tipoAlerta;

    public RegistroAlerta(TipoAlerta tipoAlerta){
        this.fecha = new Date();
        this.tipoAlerta = tipoAlerta;
    }
    public RegistroAlerta(){
        this.fecha = new Date();
    }

}
