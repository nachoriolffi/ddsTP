package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.AdapterCorreo;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.CorreoElectronico;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.ServicioMail;
import ar.edu.utn.frba.dds.models.entities.heladera.CronjobTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TestHeladera {

    Heladera heladera,heladera1, heladera2, heladera3;
    Direccion direccion1, direccion2, direccion3;
    Coordenada coordenada1, coordenada2, coordenada3;
    Vianda vianda, vianda1, vianda2, vianda3, vianda4, vianda5, vianda6;
    List<Vianda> viandas1, viandas2, viandas3,viandas;
    List<Heladera> heladeras;
    ReceptorMovimiento receptorMovimiento;
    ReceptorTemperatura receptorTemperatura;
    ModeloHeladera modeloHeladera;
    RepoHeladeras repoHeladeras;
    Tecnico tecnico1;
    RepoTecnico repoTec;

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
        viandas = new ArrayList<>();

        vianda = new Vianda("Sopa", new Colaborador(), true);
        vianda1 = new Vianda("Carne", new Colaborador(), true);
        vianda2 = new Vianda("Papas", new Colaborador(), false);
        vianda3 = new Vianda("Milanesa", new Colaborador(), true);
        vianda4 = new Vianda("Pizza", new Colaborador(), false);
        vianda5 = new Vianda("Fideos", new Colaborador(), true);
        vianda6 = new Vianda("Ravioles", new Colaborador(), true);

        heladera = new Heladera(direccion1, coordenada1, 150, viandas);
        heladera1 = new Heladera(direccion1, coordenada1, 150, viandas1);
        heladera1.setEstaActiva(Boolean.TRUE);
        heladera2 = new Heladera(direccion2, coordenada2, 200, viandas2);
        heladera3 = new Heladera(direccion3, coordenada3, 300, viandas3);

        heladera.agregarVianda(vianda);
        heladera1.agregarVianda(vianda1);
        heladera1.agregarVianda(vianda4);
        heladera2.agregarVianda(vianda2);
        heladera2.agregarVianda(vianda5);
        heladera3.agregarVianda(vianda3);
        heladera3.agregarVianda(vianda6);

        receptorMovimiento= new ReceptorMovimiento();
        receptorTemperatura= new ReceptorTemperatura();

        modeloHeladera = new ModeloHeladera(18.0,1.5,100.0,200);

        heladera.setModelo(modeloHeladera);

        repoHeladeras = RepoHeladeras.getInstancia();

        //--------------------------------------- Noticacion tecnico --------------------------------------------
        tecnico1 = new Tecnico(6L,"Juan","Cracio",coordenada1,Boolean.TRUE,1000);
        Contacto contacto = new Contacto(TipoContacto.MAIL,"clazarte@frba.utn.edu.ar");
        tecnico1.setContactos(new ArrayList<>());
        tecnico1.getContactos().add(contacto);

        ServicioMail servicioMail= ServicioMail.getInstance();
        AdapterCorreo AdapterCorreo = new AdapterCorreo(servicioMail);

        CorreoElectronico correoElectronico = new CorreoElectronico(AdapterCorreo);

        List<MedioDeComunicacion> mediosDeComunicacion = new ArrayList<>();
        mediosDeComunicacion.add(correoElectronico);

        tecnico1.setMediosDeComunicacion(mediosDeComunicacion);

        repoTec = RepoTecnico.getInstancia();
        repoTec.agregarTecnico(tecnico1);
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
        heladeraAModificar.setEstaActiva(Boolean.FALSE);


        assert heladeraAModificar.getDireccion().equals(direccion2);
        assert heladeraAModificar.getEstaActiva().equals(Boolean.FALSE);

    }

    @Test
    public void TestEstadoTiempoRealHeladera(){

        assert heladera1.getEstaActiva().equals(Boolean.TRUE);
    }

    @Test
    public void TestReceptoresTemperaturaFueraRango(){
        heladera1.setModelo(modeloHeladera);
        System.out.println(heladera1.getEstaActiva().equals(Boolean.TRUE));


        //heladera1.setReceptorTemperatura(receptorTemperatura);

        receptorTemperatura.evaluarTemperatura("19.0",heladera1);

        System.out.println(heladera1.getIncidentes().get(0).getTipoAlerta().equals(TipoAlerta.TEMPERATURA));
        System.out.println(receptorTemperatura.getTemperaturasLeidas().get(0));
        System.out.println(heladera1.getEstaActiva().equals(Boolean.FALSE));
        assert heladera1.getIncidentes().size() == 1;
    }

    @Test
    public void TestReceptoresTemperaturaEntreRango(){

        heladera1.setModelo(modeloHeladera);
        System.out.println(heladera1.getEstaActiva());
        //heladera1.setReceptorTemperatura(receptorTemperatura);
        receptorTemperatura.evaluarTemperatura("14.0",heladera1);
        System.out.println(heladera1.getEstaActiva().equals(Boolean.TRUE));
        System.out.println(receptorTemperatura.getTemperaturasLeidas().get(0));
        assert heladera1.getIncidentes().size() == 0;
    }

    @Test
    public void testCronjobTemperaturaSensorFalla(){
        // Se simula una lectura de temperatura con tiempo mayor a 5 minutos
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MINUTE, -6);
        Date fechaUltimaLectura = calendar.getTime();
        RegistroTemperatura registro = new RegistroTemperatura( Float.parseFloat("18.0"), fechaUltimaLectura);
        receptorTemperatura.getTemperaturasLeidas().add(registro);

        heladera.setReceptorTemperatura(receptorTemperatura);
        heladera.setEstaActiva(true);

        heladera.setNombre("Heladera1");

        repoHeladeras.agregarHeladera(heladera);

        CronjobTemperatura.main(null);

        assert heladera.getEstaActiva().equals(Boolean.FALSE);
    }
    @Test
    public void testCronjobTemperaturaSensorFunciona(){
        receptorTemperatura.evaluarTemperatura("18.0", heladera);
        heladera.setReceptorTemperatura(receptorTemperatura);
        heladera.setEstaActiva(true);
        heladera.setNombre("Heladera2");

        repoHeladeras.agregarHeladera(heladera);
        CronjobTemperatura.main(null);

        assert heladera.getEstaActiva().equals(Boolean.TRUE);
    }

}

