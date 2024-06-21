package ar.edu.utn.frba.dds.models.entities.heladera.receptor;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter

public class ReceptorTemperatura {

    private Heladera heladera;

    private List<Float> temperaturasLeidas;

    public ReceptorTemperatura(List<Float> temperaturasLeidas) {
        this.temperaturasLeidas = temperaturasLeidas;
    }

    public ReceptorTemperatura() {
        this.temperaturasLeidas= new ArrayList<>();
    }

    public void agregarTemperatura(float temperatura) {
        this.temperaturasLeidas.add(temperatura);
    }

    public void evaluarTemperatura(String dato, Heladera heladera) {
         if (evaluarLimitesTemperatura(Float.parseFloat(dato),heladera)){
             registrarAlerta(heladera, TipoAlerta.TEMPERATURA);
             heladera.setEstaActiva(false);
         }
        temperaturasLeidas.add(Float.parseFloat(dato));
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
    public void registrarAlerta(Heladera heladera, TipoAlerta tipoAlerta) {
        Incidente registro = new Incidente(tipoAlerta);
        heladera.agregarRegistroDeAlerta(registro);
    }
}
