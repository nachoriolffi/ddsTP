package ar.edu.utn.frba.dds.server.crons;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.CronJobReporte;
import ar.edu.utn.frba.dds.models.entities.heladera.CronjobTemperatura;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerMain {
    public static void main(String[] args) {

        // Crea el ScheduledExecutorService con un solo hilo
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(8);

        CronJobReporte jobReporte = new CronJobReporte();



        // Programa el job para que se ejecute cada 7 días
        long initialDelay = 0;  // Tiempo de espera inicial antes de la primera ejecución
        long period = 1;        // Intervalo de ejecución en días (1 semana)

        scheduler.scheduleAtFixedRate(
                jobReporte::ejecutarReporte,
                initialDelay,
                period,
                TimeUnit.MINUTES
        );

        long initialDelayTemperatura = 0;  // Tiempo de espera inicial antes de la primera ejecución
        long periodTemperatura = 1;        // Intervalo de ejecución en días (1 día)

        CronjobTemperatura jobTemperatura = new CronjobTemperatura();

        scheduler.scheduleAtFixedRate(
                jobTemperatura::ejecutarTemperatura,
                initialDelayTemperatura,
                periodTemperatura,
                TimeUnit.MINUTES
        );

        System.out.println("Scheduler iniciado para la generación de reportes cada semana y para revisar la temperatura de una heladera.");
    }
}
