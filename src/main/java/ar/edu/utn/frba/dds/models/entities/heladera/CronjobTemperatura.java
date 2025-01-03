package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente.FALLA;

public class CronjobTemperatura {
    public void ejecutarTemperatura() {

        System.out.println("Ejecutando cronjob de temperatura");

        List<Heladera> todasLasHeladeras = RepoHeladeras.INSTANCE.buscarTodos();

        controlarUltimasLecturasHeladeras(todasLasHeladeras, 1);// es el tiempo tope en el que si lo supera hay un problema

        todasLasHeladeras.forEach(heladera ->
        {
        if(heladera.getEstaActiva())System.out.println("La "+heladera.getNombre()+" esta activa");
        else System.out.println("La "+heladera.getNombre()+ " no esta activa");
        }
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

                Incidente incidente = new Incidente("CronjobTemeratura","",FALLA,null,null);
                incidente.setHeladera(heladera);
                incidente.setFecha(new Date());
                incidente.setDescripcion("La heladera " + heladera.getNombre() + " no ha enviado lecturas de temperatura en los ultimos " + tiempoEnMinutos + " minutos");
                RepoIncidente.INSTANCE.agregar(incidente);
                heladera.agregarRegistroDeAlerta(incidente);
                RepoHeladeras.INSTANCE.modificar(heladera);
                incidente.notificarTecnicoMasCercano(heladera);

            }
            else {
                System.out.println("La diferencia entre la hora de la ultima lectura y la hora actual no es mayor a " + tiempoEnMinutos + " minutos");
            }

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String fechaFormateada = formato.format(ultimaLectura.getFechaHora());
            System.out.println("La temperatura leida es: " + ultimaLectura.getLectura() + " y la fecha y hora es: " + fechaFormateada);
            //Broker broker = Broker.getInstance();
            //broker.publish("dds2024/heladera/"+heladera.getNombre()+"/sensor/temperatura",ultimaLectura.getLectura().toString());
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

    public CronjobTemperatura() {
    }

}