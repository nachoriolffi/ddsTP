package ar.edu.utn.frba.dds.heladera;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter

public class ReceptorTemperatura {

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

    public void evaluarTemperatura(String dato,Heladera heladera) {
         if (evaluarLimitesTemperatura(Float.parseFloat(dato),heladera)){
             registrarAlerta(heladera,TipoAlerta.TEMPERATURA);
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
        RegistroDeAlerta registro = new RegistroDeAlerta(tipoAlerta);
        heladera.agregarRegistroDeAlerta(registro);
    }
}
