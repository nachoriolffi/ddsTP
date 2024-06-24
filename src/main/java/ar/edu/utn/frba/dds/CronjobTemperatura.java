package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CronjobTemperatura {
    public static void main(String[] args) {
        Heladera heladera;
        Direccion direccion = new Direccion("Medrano", 951, 1);
        Coordenada coordenada = new Coordenada(125.0, 410.0);

        List<Vianda> viandas = new ArrayList<>();
        heladera = new Heladera(direccion, coordenada, 150, viandas);

        Vianda vianda = new Vianda("Carne", heladera, new Colaborador(), true);
        heladera.agregarVianda(vianda);

        ModeloHeladera modelo = new ModeloHeladera(18.0, 1.5, 100.0, 200);

        heladera.setModelo(modelo);

        ReceptorTemperatura receptorTemp = new ReceptorTemperatura(); // se tiene un receptor por cada heladera
        receptorTemp.evaluarTemperatura("18.0", heladera);

        heladera.setReceptorTemperatura(receptorTemp);

        List<RegistroTemperatura> temperaturas = new ArrayList<>(heladera.getReceptorTemperatura().getTemperaturasLeidas());
        RegistroTemperatura ultimaLectura = temperaturas.get(temperaturas.size() - 1);

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fechaFormateada = formato.format(ultimaLectura.getFechaHora());

        System.out.println("La temperatura leida es: " + ultimaLectura.getLectura() + " y la fecha y hora es: " + fechaFormateada);

        //assert ultimaLectura.getLectura() == 18;
        // crear receptor de temperatura, crear modelo

    }

}
/*
Tengo una lista con varias heladeras y dentro está el sensor y dentro
receptorTemperatura. Dentro del receptor está la lista de temperaturas leídas.
Lo que importa es el horario. es decir, obtener un elemento y controlar el horario.
*/