package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.CronjobTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;


public class testCronJobTemperatura {


    RegistroTemperatura registroTemperatura;

    Heladera heladera;

    ReceptorTemperatura receptorTemperatura;

    Colaborador colaborador;

    ModeloHeladera modeloHeladera;



    @BeforeEach
    public void setUp() {

        //para hacer esto necesito healdera y que tenga un registro de temperatura
        receptorTemperatura = new ReceptorTemperatura();
        RepoReceptorTemperatura.INSTANCE.agregar(receptorTemperatura);

        Coordenada coordenadaHeladera = new Coordenada();
        coordenadaHeladera.setLatitud(-34.603722);
        coordenadaHeladera.setLongitud(-58.381592);
        // Save the coordenadaHeladera if necessary

        // Create and save the coordinates for the tecnico
        Coordenada coordenadaTecnico = new Coordenada();
        coordenadaTecnico.setLatitud(-34.615803);
        coordenadaTecnico.setLongitud(-58.433298);

        RepoCoordenada.INSTANCE.agregar(coordenadaHeladera);
        RepoCoordenada.INSTANCE.agregar(coordenadaTecnico);

        ModeloHeladera modeloHeladera = new ModeloHeladera();
        modeloHeladera.setTemperaturaMinima(0.0);
        modeloHeladera.setTemperaturaMaxima(10.0);
        RepoModelo.INSTANCE.agregar(modeloHeladera);

        // Create the tecnico and associate the coordinate
        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Tecnico1");
        tecnico.setApellido("Apellido1");
        tecnico.setCoordenada(coordenadaTecnico);
        tecnico.setAreaCobertura((int) 10.0);
        tecnico.setDni(Integer.valueOf("12345678"));
        tecnico.setCuil(Integer.valueOf("12345678"));
        tecnico.setTipoDocumento(TipoDocumento.DNI);
        tecnico.setDisponible(true);
        RepoTecnico.INSTANCE.agregar(tecnico);

        heladera = new Heladera();
        heladera.setNombre("heladeraFede");
        heladera.setEstaActiva(true);
        heladera.setModelo(modeloHeladera);
        heladera.setReceptorTemperatura(receptorTemperatura);
        heladera.setCoordenada(coordenadaHeladera);
        RepoHeladeras.INSTANCE.agregar(heladera);

        registroTemperatura = new RegistroTemperatura();
        registroTemperatura.setLectura(5.0F);
        registroTemperatura.setFechaHora(new Date());
        RepoRegistroTemperatura.INSTANCE.agregar(registroTemperatura);

        receptorTemperatura.agregarRegistro(registroTemperatura);
        RepoReceptorTemperatura.INSTANCE.modificar(receptorTemperatura);

    }

    @Test
    public void testControlarUltimaLectura() {
        //controlarUltimaLectura(heladera, 5);
        CronjobTemperatura cron = new CronjobTemperatura();
        cron.ejecutarTemperatura();
    }

    @Test
    public void testCrontrolarUltimaLecturaFunciona() {

        CronjobTemperatura cron = new CronjobTemperatura();

        cron.ejecutarTemperatura();
    }
}
