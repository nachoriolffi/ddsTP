package ar.edu.utn.frba.dds.models.entities.heladera.receptor;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
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

        //registrarAlerta(heladera, TipoAlerta.ROBO);
       reportarAlerta(heladera, TipoAlerta.ROBO);

        //throw new RuntimeException("Se detecto movimiento en la heladera");
        // tengo mis dudas de que esto se maneja en otr p[arte y noa aca en una clase de exptions


   }

    public void registrarAlerta(Heladera heladera, TipoAlerta tipoAlerta) {
        Incidente registro = new Incidente(tipoAlerta);
        heladera.agregarRegistroDeAlerta(registro);
    }

    public void reportarAlerta(Heladera heladera, TipoAlerta tipoAlerta){
        Broker broker = new Broker();
        broker.connect(heladera.getNombre());
        broker.publish("dds2024/heladera/" + heladera.getNombre() + "/alerta", tipoAlerta.toString());
        //En consola se veria: dds2024/heladera/medrano/alerta: robo
        broker.disconnect();
    }

}
