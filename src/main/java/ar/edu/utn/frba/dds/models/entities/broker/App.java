package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        RepoHeladeras repoHeladeras = new RepoHeladeras();
        RepoTarjeta repoTarjeta = new RepoTarjeta(); //Hay dos repos de tarjeta, preguntar
        RepoColaborador repoColaborador = new RepoColaborador();

        Broker broker = new Broker();
        Heladera heladera = new Heladera();
        Heladera heladera2 = new Heladera();
        Colaborador colaborador = new Colaborador();
        Tarjeta tarjeta = new Tarjeta(21, null, colaborador);

        ReceptorMovimiento receptorMovimiento = new ReceptorMovimiento();
        ReceptorTemperatura receptorTemperatura = new ReceptorTemperatura();
        ReceptorMovimiento receptorMovimiento2 = new ReceptorMovimiento();
        ReceptorTemperatura receptorTemperatura2 = new ReceptorTemperatura();

        heladera.setReceptorMovimiento(receptorMovimiento);
        heladera.setReceptorTemperatura(receptorTemperatura);
        heladera2.setReceptorMovimiento(receptorMovimiento2);
        heladera2.setReceptorTemperatura(receptorTemperatura2);

        heladera.setCapacidad(4);

        ModeloHeladera modelo = new ModeloHeladera( 18.0, 10.0,80.0,100 );
        heladera.setModelo(modelo);
        heladera2.setModelo(modelo);


        heladera.setNombre("campus");
        heladera2.setNombre("casa");

        repoHeladeras.agregar(heladera);
        repoHeladeras.agregar(heladera2);

        repoColaborador.agregar(colaborador);
        repoTarjeta.agregar(tarjeta);

        broker.connect("escucha");
        //escucha.subscribe("dds2024/heladera" + heladera.getId());
        //broker.subscribe("dds2024/heladera/campus/alerta");
        broker.subscribe("heladeras/+/temperatura");
        broker.subscribe("heladeras/+/alerta");
        //receptorMovimiento.evaluarDatosSensor("activado",heladera);

        //necesito publicar una termperatura para campus
        //broker.publish("heladeras/campus/temperatura", "30");
        //broker.publish("heladeras/casa/temperatura", "30");
        //broker.publish("heladeras/casa/temperatura", "12");
        broker.publish("heladeras/casa/alerta", "");

        RegistroSolicitud registro= new RegistroSolicitud();
        registro.setTarjeta(tarjeta);
        RepoTarjeta.INSTANCE.agregar(tarjeta);

        try {
            heladera.agregarRegistroSolicitud(registro, broker);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            Thread.sleep(2000); // Esperar 2 segundos para asegurar la recepción
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(heladera.getIncidentes().size());
        System.out.println(heladera2.getIncidentes().size());
        System.out.println(heladera.getSolicitudesApertura().size());
        broker.disconnect();

    }
}