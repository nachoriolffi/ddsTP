package ar.edu.utn.frba.dds.tarjeta;

import ar.edu.utn.frba.dds.heladera.Heladera;

import java.util.Date;

public class UsoTarjeta {
    private Date fechaUso;
    private Heladera heladera;

    public UsoTarjeta(Date fechaUso, Heladera heladera) {
        this.fechaUso = fechaUso;
        this.heladera = heladera;
    }
}
