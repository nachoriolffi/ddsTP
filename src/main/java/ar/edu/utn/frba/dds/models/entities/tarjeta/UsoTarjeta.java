package ar.edu.utn.frba.dds.models.entities.tarjeta;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;

import java.util.Date;

public class UsoTarjeta {
    private Date fechaUso;
    private Heladera heladera;

    public UsoTarjeta(Date fechaUso, Heladera heladera) {
        this.fechaUso = fechaUso;
        this.heladera = heladera;
    }
}
