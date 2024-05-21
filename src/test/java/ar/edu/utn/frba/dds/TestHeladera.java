package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.heladera.Vianda;
import ar.edu.utn.frba.dds.utils.Calle;
import ar.edu.utn.frba.dds.utils.Coordenada;
import ar.edu.utn.frba.dds.utils.Direccion;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestHeladera {

    Heladera heladera1, heladera2, heladera3;
    Direccion direccion1, direccion2, direccion3;
    Coordenada coordenada1, coordenada2, coordenada3;
    Vianda vianda1, vianda2, vianda3, vianda4, vianda5, vianda6;
    List<Vianda> viandas1, viandas2, viandas3;
    List<Heladera> heladeras;

    @BeforeEach
    public void seteoHeladeras() {

        heladeras = new ArrayList<>();

        direccion1 = new Direccion("Medrano", 951, 1);
        direccion2 = new Direccion("Rivadavia", 10400, 0);
        direccion3 = new Direccion("Mozart", 10, 1);

        coordenada1 = new Coordenada(125.0, 410.0);
        coordenada2 = new Coordenada(200.0, 1254.0);
        coordenada3 = new Coordenada(500.0, 153.0);

        viandas1 = new ArrayList<>();
        viandas2 = new ArrayList<>();
        viandas3 = new ArrayList<>();

        vianda1 = new Vianda("Carne", heladera1, new Colaborador(), true);
        vianda2 = new Vianda("Papas", heladera2, new Colaborador(), false);
        vianda3 = new Vianda("Milanesa", heladera3, new Colaborador(), true);
        vianda4 = new Vianda("Pizza", heladera1, new Colaborador(), false);
        vianda5 = new Vianda("Fideos", heladera2, new Colaborador(), true);
        vianda6 = new Vianda("Ravioles", heladera3, new Colaborador(), true);

        heladera1 = new Heladera(direccion1, coordenada1, 150, viandas1);
        heladera2 = new Heladera(direccion2, coordenada2, 200, viandas2);
        heladera3 = new Heladera(direccion3, coordenada3, 300, viandas3);

        heladera1.agregarVianda(vianda1);
        heladera1.agregarVianda(vianda4);
        heladera2.agregarVianda(vianda2);
        heladera2.agregarVianda(vianda5);
        heladera3.agregarVianda(vianda3);
        heladera3.agregarVianda(vianda6);

    }

    @Test
    public void TestAgregarHeladera() {

        heladeras.add(heladera1);
        heladeras.add(heladera2);
        heladeras.add(heladera3);

        assert heladeras.size() == 3;
    }

    @Test
    public void TestDarDeBajaHeladera() {

        heladeras.add(heladera1);
        heladeras.add(heladera2);
        heladeras.add(heladera3);

        System.out.println("Cantidad de heladeras antes: " + heladeras.size());

        Heladera heladeraQuitada1 = heladeras.remove(0);
        Heladera heladeraQuitada2 = heladeras.remove(1);

        System.out.println(heladeraQuitada1.getViandas());
        System.out.println(heladeraQuitada2.getViandas());

        System.out.println("Cantidad de heladeras despues: " + heladeras.size());

        assert heladeras.size() == 1;

    }

    @Test
    public void TestModificarHeladera() {

        heladeras.add(heladera1);
        heladeras.add(heladera2);
        heladeras.add(heladera3);

        Heladera heladeraAModificar = heladeras.get(0);
        heladeraAModificar.setDireccion(direccion2);
        assert heladeraAModificar.getDireccion().equals(direccion2);


    }
}


