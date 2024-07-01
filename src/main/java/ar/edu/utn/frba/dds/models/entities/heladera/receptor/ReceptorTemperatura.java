package ar.edu.utn.frba.dds.models.entities.heladera.receptor;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter

public class ReceptorTemperatura {

    private Heladera heladera;

    @Setter
    private List<RegistroTemperatura> temperaturasLeidas;

    public ReceptorTemperatura(List<RegistroTemperatura> temperaturasLeidas) {
        this.temperaturasLeidas = temperaturasLeidas;
    }

    public ReceptorTemperatura() {
        this.temperaturasLeidas= new ArrayList<>();
    }

    public void agregarTemperatura(float temperatura) {//preguntar para que lo usa
        //this.temperaturasLeidas.add(temperatura);
    }

    public void evaluarTemperatura(String dato, Heladera heladera) {
        if (evaluarLimitesTemperatura(Float.parseFloat(dato),heladera)){
            registrarIncidente(heladera, TipoAlerta.TEMPERATURA);
        }
        //hay que hacer un RegistroTemperatura y despues agregarlo
        RegistroTemperatura registro = new RegistroTemperatura( Float.parseFloat(dato), new Date());

        temperaturasLeidas.add(registro);
    }

    private Boolean evaluarLimitesTemperatura(Float temperatura, Heladera heladera) {
        return evaluarTemperaturaMaxima(temperatura,heladera) || evaluarTemperaturaMinima(temperatura,heladera);
    }

    private boolean evaluarTemperaturaMaxima(Float temperatura, Heladera heladera) {
        return temperatura > heladera.getModelo().getTemperaturaMaxima();
    }

    private boolean evaluarTemperaturaMinima(Float temperatura, Heladera heladera) {
        return temperatura < heladera.getModelo().getTemperaturaMinima();
    }
    public void registrarIncidente(Heladera heladera, TipoAlerta tipoAlerta) {
        Incidente registro = new Incidente(tipoAlerta);
        registro.notificarTecnicoMasCercano(heladera);
        heladera.agregarRegistroDeAlerta(registro);
        heladera.setEstaActiva(false);
    }

    public void reportarAlerta(Heladera heladera){
        Broker broker = new Broker();
        broker.publish("dds2024/heladera" + heladera.getNombre() + "/alerta", TipoAlerta.TEMPERATURA.toString());
        //En consola se veria: dds2024/heladera/medrano/alerta: temperatura
        broker.disconnect();
    }
}
