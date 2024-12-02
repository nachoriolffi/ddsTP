package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReceptorTemperatura;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Getter
@Setter
public class TestBroker {
    private Heladera heladera;
    private ReceptorTemperatura receptorTemperatura;
    private ReceptorMovimiento receptorMovimiento;
    private List<Incidente> incidentes;
    private ModeloHeladera modeloHeladera;
    private RepoHeladeras repoHeladeras;
    private RegistroSolicitud registroSolicitud;
    private Vianda vianda;


    private Broker broker;

    @BeforeEach
    public void setUp(){
        heladera = new Heladera();
        incidentes = heladera.getIncidentes();
        receptorMovimiento= new ReceptorMovimiento();
        receptorTemperatura= new ReceptorTemperatura();

        RepoReceptorTemperatura.INSTANCE.agregar(receptorTemperatura);


        modeloHeladera = new ModeloHeladera(12.5,0.0,90.0,15);
        heladera.setNombre("medrano");
        heladera.setModelo(modeloHeladera);
        heladera.setReceptorTemperatura(receptorTemperatura);
        heladera.setReceptorMovimiento(receptorMovimiento);


        RepoHeladeras.INSTANCE.agregar(heladera);

        broker = new Broker();
        broker.connect("escuchadno");
    }


    @Test
    public void testAlerta(){
        broker.subscribe("dds2024/heladera/medrano/alerta");
        receptorMovimiento.evaluarDatosSensor("1",heladera);
        broker.disconnect();
    }

    @Test
    public void probamosHeladeraYBroker(){
        broker.subscribe("heladeras/+/temperatura");
        String topic = "heladeras/"+"1"+"/temperatura";
        String temperatureValue = "22.5";
        broker.publish(topic,temperatureValue);
        //receptorMovimiento.evaluarDatosSensor("1",heladera);
        //receptorTemperatura.evaluarTemperatura("1",heladera);
       // broker.disconnect();
    }
}