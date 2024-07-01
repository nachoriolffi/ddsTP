package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CronjobTemperatura {
    public static void main(String[] args) {

        RepoHeladeras repoHeladeras = RepoHeladeras.getInstancia();

        List<Heladera> todasLasHeladeras = repoHeladeras.traerHeladeras();

        controlarUltimasLecturasHeladeras(todasLasHeladeras, 5);

        todasLasHeladeras.forEach(heladera3 -> { if(heladera3.getEstaActiva()){System.out.println("La "+heladera3.getNombre()+" esta activa");}
        else{System.out.println("La "+heladera3.getNombre()+ " no esta activa");}}
        );

    }
    static void controlarUltimaLectura(Heladera heladera, long tiempoEnMinutos){
        List<RegistroTemperatura> temperaturas = new ArrayList<>(heladera.getReceptorTemperatura().getTemperaturasLeidas());
        RegistroTemperatura ultimaLectura = null;
        if (!temperaturas.isEmpty()) {
            ultimaLectura = temperaturas.get(temperaturas.size() - 1);

            Date fechaUltimaLectura = ultimaLectura.getFechaHora();
            Date fechaActual = new Date();

            long diferenciaMilisegundos = Math.abs(fechaActual.getTime() - fechaUltimaLectura.getTime());
            long diferenciaMinutos = TimeUnit.MINUTES.convert(diferenciaMilisegundos, TimeUnit.MILLISECONDS);

            if (diferenciaMinutos > tiempoEnMinutos) {
                System.out.println("La diferencia entre la hora de la ultima lectura y la hora actual es mayor a " + tiempoEnMinutos + " minutos");
                heladera.setEstaActiva(false);
                /*
                   reportar el incidente indicando la fecha y hora del mismo, en qué heladera ocurrió
                   y el tipo. En caso de que sea una alerta indicar cuál fue.
                */
            }
            else {
                System.out.println("La diferencia entre la hora de la ultima lectura y la hora actual no es mayor a " + tiempoEnMinutos + " minutos");
            }

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String fechaFormateada = formato.format(ultimaLectura.getFechaHora());
            System.out.println("La temperatura leida es: " + ultimaLectura.getLectura() + " y la fecha y hora es: " + fechaFormateada);
        }
        else {
            System.out.println("La lista de temperaturas esta vacia.");
        }
    }

    static void controlarUltimasLecturasHeladeras(List<Heladera> heladeras, long tiempoEnMinutos) {
        for (Heladera heladera : heladeras) {
            controlarUltimaLectura(heladera, tiempoEnMinutos);
        }
    }

}