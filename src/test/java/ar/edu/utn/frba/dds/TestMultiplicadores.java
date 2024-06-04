package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.colaborador.calculoPuntos.CalculadorPuntos;
import ar.edu.utn.frba.dds.colaborador.formasColab.*;
import ar.edu.utn.frba.dds.reconocimiento.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.vianda.Vianda;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.ubicacionGeografica.Direccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestMultiplicadores {

    List<Heladera> heladeras = new ArrayList<>();
    Colaborador colaborador;

    @BeforeEach
    public void seteoHeladeras() {

        Direccion direccion1 = new Direccion("Medrano", 951, 1);
        Direccion direccion2 = new Direccion("Rivadavia", 10400, 0);
        Direccion direccion3 = new Direccion("Mozart", 10, 1);

        Coordenada coordenada1 = new Coordenada(125.0, 410.0);
        Coordenada coordenada2 = new Coordenada(200.0, 1254.0);
        Coordenada coordenada3 = new Coordenada(500.0, 153.0);

        Date fecha1 = new Date(2024, 02, 14);
        Date fecha2 = new Date(2024, 05, 10);
        Date fecha3 = new Date(2024, 04, 04);

        Heladera heladera1 = new Heladera(direccion1, coordenada1, 150, new ArrayList<>(), fecha1);
        Heladera heladera2 = new Heladera(direccion2, coordenada2, 200, new ArrayList<>(), fecha2);
        Heladera heladera3 = new Heladera(direccion3, coordenada3, 300, new ArrayList<>(), fecha3);

        Vianda vianda1 = new Vianda("Carne", heladera1, new Colaborador(), true);
        Vianda vianda2 = new Vianda("Papas", heladera2, new Colaborador(), false);
        Vianda vianda3 = new Vianda("Milanesa", heladera3, new Colaborador(), true);
        Vianda vianda4 = new Vianda("Pizza", heladera1, new Colaborador(), false);
        Vianda vianda5 = new Vianda("Fideos", heladera2, new Colaborador(), true);
        Vianda vianda6 = new Vianda("Ravioles", heladera3, new Colaborador(), true);

        heladera1.agregarVianda(vianda1);
        heladera1.agregarVianda(vianda4);
        heladera2.agregarVianda(vianda2);
        heladera2.agregarVianda(vianda5);
        heladera3.agregarVianda(vianda3);
        heladera3.agregarVianda(vianda6);

        heladeras.add(heladera1);
        heladeras.add(heladera2);
        heladeras.add(heladera3);

    }

    // TODO
    // tenemos que cambiar estos test al haber borrado el dato de puntos totales y chequear que mas rompe
    @BeforeEach
    public void seteoColaborador() {

        List<FormaDeColaboracion> colaboracionesRalizadas = new ArrayList<>();

        // FORMAS DE COLABORACION
        DonacionDinero donacionDinero = new DonacionDinero(1000, new Date());
        DonacionDinero donacionDinero1 = new DonacionDinero(2000, new Date());
        DonacionVianda donacionVianda = new DonacionVianda(10, new Date());
        HacerseCargoDeHeladera hacerseCargoDeHeladera = new HacerseCargoDeHeladera(heladeras, TipoColaboracion.HACERSE_CARGO_HELADERA);
        // AGREGAMOS FORMAS DE COLABORAR HECHAS AL COLABORADOR
        colaboracionesRalizadas.add(donacionDinero);
        colaboracionesRalizadas.add(donacionDinero1);
        colaboracionesRalizadas.add(donacionVianda);
        colaboracionesRalizadas.add(hacerseCargoDeHeladera);

        colaborador = new Colaborador("Juan", "Perez", colaboracionesRalizadas);
    }

    @Test
    public void testSumarPuntosA() {
        ConfiguracionMultiplicador.getInstance();
        CalculadorPuntos calculadorPuntos = CalculadorPuntos.getInstancia();


        System.out.println("Puntos totales: " + calculadorPuntos.sumarPuntosA(colaborador));
        // 1000*1+2000*1+10*0.5
        assert calculadorPuntos.sumarPuntosA(colaborador) == 3005.0;
    }

    @Test
    public void testRecibirMultiplicadorDinero() throws Exception {
        DonacionDinero donacionDinero= new DonacionDinero(1000,new Date());

        System.out.println("Multiplicador antes de modificar: "+donacionDinero.getMultiplicador());

        ConfiguracionMultiplicador configuracionMultiplicador = ConfiguracionMultiplicador.getInstance();
        configuracionMultiplicador.setMultiplicadorDinero(5.0);



        // Notificar manualmente la actualización a la instancia de DonacionDinero
        donacionDinero.update(configuracionMultiplicador, null);

        System.out.println("Multiplicador despues de modificar: "+donacionDinero.getMultiplicador());

        assert donacionDinero.getMultiplicador() == 5.0;
    }
    }





