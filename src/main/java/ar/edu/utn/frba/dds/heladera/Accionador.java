package ar.edu.utn.frba.dds.heladera;

import ar.edu.utn.frba.dds.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.heladera.alerta.TipoAlerta;

public class Accionador {

    private static Accionador instancia = null;

    public static Accionador getInstancia(){
        if (instancia == null){
            instancia = new Accionador();
        }
        return instancia;
    }
    public void registrarAlerta(Heladera heladera, TipoAlerta tipoAlerta) {
        Incidente registro = new Incidente(tipoAlerta);
        heladera.agregarRegistroDeAlerta(registro);
    }
}
