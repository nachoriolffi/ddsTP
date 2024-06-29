package ar.edu.utn.frba.dds.models.entities.heladera;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CronjobTemperatura {
    public static void main(String[] args) {

        Direccion direccion = new Direccion("Medrano", 951, 1);
        Coordenada coordenada = new Coordenada(125.0, 410.0);

        List<Vianda> viandas = new ArrayList<>();

        Heladera heladera;
        heladera = new Heladera(direccion, coordenada, 150, viandas);

        Vianda vianda = new Vianda("Carne", heladera, new Colaborador(), true);
        heladera.agregarVianda(vianda);

        ModeloHeladera modelo = new ModeloHeladera(18.0, 1.5, 100.0, 200);

        heladera.setModelo(modelo);

        ReceptorTemperatura receptorTemp = new ReceptorTemperatura(); // se tiene un receptor por cada heladera
        //receptorTemp.evaluarTemperatura("18.0", heladera);

        // Se simula una lectura de temperatura con tiempo mayor a 5 minutos---------------
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MINUTE, -6);
        Date fechaUltimaLectura = calendar.getTime();
        RegistroTemperatura registro = new RegistroTemperatura( Float.parseFloat("18.0"), fechaUltimaLectura);
        receptorTemp.getTemperaturasLeidas().add(registro);
        //-----------------------------------------------------------------------------------

        heladera.setReceptorTemperatura(receptorTemp);
        heladera.setEstaActiva(true);
        RepoHeladeras repoHeladeras = RepoHeladeras.getInstancia();
        repoHeladeras.agregarHeladera(heladera);

        Heladera heladera2;
        heladera2 = new Heladera(direccion, coordenada, 150, viandas);
        heladera2.agregarVianda(vianda);
        heladera2.setModelo(modelo);
        ReceptorTemperatura receptorTemp2 = new ReceptorTemperatura();
        receptorTemp2.evaluarTemperatura("18.0", heladera);
        heladera2.setReceptorTemperatura(receptorTemp2);
        heladera2.setEstaActiva(true);
        repoHeladeras.agregarHeladera(heladera2);


        List<Heladera> todasLasHeladeras = repoHeladeras.traerHeladeras();

        controlarUltimasLecturasHeladeras(todasLasHeladeras, 5);


        if(heladera.getEstaActiva()){
            System.out.println("La heladera está activa");
        }
        else{
            System.out.println("La heladera no está activa");
        }

        if(heladera2.getEstaActiva()){
            System.out.println("La heladera2 está activa");
        }
        else{
            System.out.println("La heladera2 no está activa");
        }
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
                System.out.println("La diferencia entre la hora de la última lectura y la hora actual es mayor a " + tiempoEnMinutos + " minutos");
                heladera.setEstaActiva(false);
                /*
                   reportar el incidente indicando la fecha y hora del mismo, en qué heladera ocurrió
                   y el tipo. En caso de que sea una alerta indicar cuál fue.
                */
            }
            else {
                System.out.println("La diferencia entre la hora de la última lectura y la hora actual no es mayor a " + tiempoEnMinutos + " minutos");
            }

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String fechaFormateada = formato.format(ultimaLectura.getFechaHora());
            System.out.println("La temperatura leida es: " + ultimaLectura.getLectura() + " y la fecha y hora es: " + fechaFormateada);
        }
        else {
            System.out.println("La lista de temperaturas está vacía.");
        }
    }

    static void controlarUltimasLecturasHeladeras(List<Heladera> heladeras, long tiempoEnMinutos) {
        for (Heladera heladera : heladeras) {
            controlarUltimaLectura(heladera, tiempoEnMinutos);
        }
    }

}