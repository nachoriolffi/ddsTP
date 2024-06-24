package ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class RegistroTemperatura {
    Float lectura;
    Date fechaHora;

    public RegistroTemperatura(Float lectura, Date fechaHora) {
        this.lectura = lectura;
        this.fechaHora = fechaHora;
    }
}
