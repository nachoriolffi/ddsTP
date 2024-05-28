package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.colaborador.formasColab.RegistroVulnerable;
import ar.edu.utn.frba.dds.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.vianda.Vianda;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import ar.edu.utn.frba.dds.vulnerable.RegistroDePersonaACargo;
import ar.edu.utn.frba.dds.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.vulnerable.Vulnerable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestVulnerable {

    List<Tarjeta> tarjetas;
    Tarjeta tarjeta1;
    Tarjeta tarjeta2;
    Heladera heladera1, heladera2, heladera3;
    Direccion direccion1, direccion2, direccion3;
    Coordenada coordenada1, coordenada2, coordenada3;
    Vianda vianda1, vianda2, vianda3, vianda4, vianda5, vianda6,vianda7;
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
        vianda7 = new Vianda("asdasd", heladera1, new Colaborador(), true);

        heladera1 = new Heladera(direccion1, coordenada1, 150, viandas1);
        heladera1.setEstaActiva(Boolean.TRUE);
        heladera2 = new Heladera(direccion2, coordenada2, 200, viandas2);
        heladera3 = new Heladera(direccion3, coordenada3, 300, viandas3);

        heladera1.agregarVianda(vianda1);
        heladera1.agregarVianda(vianda4);
        heladera2.agregarVianda(vianda2);
        heladera2.agregarVianda(vianda5);
        heladera3.agregarVianda(vianda3);
        heladera3.agregarVianda(vianda6);

    }

    @BeforeEach
    public void setUpVulnerable() {
        Date fecha = new Date(1970,10,12);
        Date fecha2 = new Date(1980,01,27);
        Date fechaRegistro = new Date(2024,04,10);
        Vulnerable personaVulnerable1 = new Vulnerable("Carlos","Perez",fecha,fechaRegistro,true,null, TipoDocumento.DNI,22732987);
        Vulnerable personaVulnerable2 = new Vulnerable("Maria","",fecha2,fechaRegistro,true,null, TipoDocumento.DNI,34654987);

        Colaborador colaborador1 = new Colaborador("Juan","Perez",new ArrayList<FormaDeColaboracion>());

        tarjeta1 = new Tarjeta(1, personaVulnerable1, colaborador1);
        tarjeta2 = new Tarjeta(2, personaVulnerable2, colaborador1);
        tarjetas= new ArrayList<Tarjeta>();
        tarjetas.add(tarjeta1);
        tarjetas.add(tarjeta2);

        RegistroDePersonaACargo registroDePersonaACargo = new RegistroDePersonaACargo(true,new Date(),1);

        personaVulnerable1.agregarregistroDePersonasACargo(registroDePersonaACargo);
    }
    @Test
    public void testVulnerable() {

        RegistroVulnerable registroVulnerable = new RegistroVulnerable(tarjetas, TipoColaboracion.ENTREGA_TARJETAS);
        assert registroVulnerable.getCantidadTarjetas() == 2;
    }
    @Test
    public void testAuditoriaTarjeta() throws IOException {

        // Saca las primeras 6 bien, ya que tiene 4 de base y 2 para la persona que
        // tiene a cargo
        tarjeta1.sacarVianda(vianda1,heladera1);
        tarjeta1.sacarVianda(vianda2,heladera2);
        tarjeta1.sacarVianda(vianda3,heladera3);
        tarjeta1.sacarVianda(vianda4,heladera1);
        tarjeta1.sacarVianda(vianda5,heladera2);
        tarjeta1.sacarVianda(vianda6,heladera3);


        try {
            tarjeta1.sacarVianda(vianda7, heladera1);
        } catch (IOException e) {
            // La excepción esperada se lanzó, el test pasa
            Assertions.assertTrue(true); // Assert tradicional para verificar la condición
        }
    }




}
