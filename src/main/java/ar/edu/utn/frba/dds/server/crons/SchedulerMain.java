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
        CronjobTemperatura jobTemperatura = new CronjobTemperatura();
        // CRONJOB GENERACION REPORTES CADA UNA SEMANA

        long initialDelay = 0;  // Tiempo de espera inicial antes de la primera ejecución
        long period = 5;        // Intervalo de ejecución en días (1 semana)

        scheduler.scheduleAtFixedRate(
                jobReporte::ejecutarReporte,
                initialDelay,
                period,
                TimeUnit.MINUTES
        );

        // CRONJOB CHEQUEO TEMPERATURA POR HELADERA CADA 5 MINUTOS

        long initialDelayTemperatura = 0;  // Tiempo de espera inicial antes de la primera ejecución
        long periodTemperatura = 30;        // Intervalo de ejecución en minutos (5 minutos)

        scheduler.scheduleAtFixedRate(
                jobTemperatura::ejecutarTemperatura,
                initialDelayTemperatura,
                periodTemperatura,
                TimeUnit.SECONDS
        );
        System.out.println("Scheduler iniciado para la generación de reportes cada semana y para revisar la temperatura de una heladera cada 5 minutos.");
    }
}
