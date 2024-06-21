package ar.edu.utn.frba.dds.models.entities.heladera.receptor;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;

public class ReceptorMovimiento {

    private Heladera heladera;

    public ReceptorMovimiento(Heladera heladera) {
        this.heladera = heladera;
    }

    public ReceptorMovimiento() {
    }

   public void evaluarDatosSensor(String dato,Heladera heladera){

        registrarAlerta(heladera, TipoAlerta.ROBO);

        throw new RuntimeException("Se detecto movimiento en la heladera");
        // tengo mis dudas de que esto se maneja en otr p[arte y noa aca en una clase de exptions


   }

    public void registrarAlerta(Heladera heladera, TipoAlerta tipoAlerta) {
        Incidente registro = new Incidente(tipoAlerta);
        heladera.agregarRegistroDeAlerta(registro);
    }

}
