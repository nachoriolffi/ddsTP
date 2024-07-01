package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Getter
@Setter
public class TestBroker {
    private Heladera heladera;
    private ReceptorTemperatura receptorTemperatura;
    private ReceptorMovimiento receptorMovimiento;
    //Agregar lo de Autorizacion
    private ModeloHeladera modeloHeladera;
    private RepoHeladeras repoHeladeras;

    private Broker broker;

    @BeforeEach
    public void setUp(){
        heladera = new Heladera();
        receptorMovimiento= new ReceptorMovimiento();
        receptorTemperatura= new ReceptorTemperatura();

        modeloHeladera = new ModeloHeladera(12.5,0.0,90.0,15);
        heladera.setNombre("medrano");
        heladera.setModelo(modeloHeladera);

        repoHeladeras = RepoHeladeras.getInstancia();
        repoHeladeras.agregarHeladera(heladera);

        broker = new Broker();
    }

    @Test
    public void TestTemperatura(){
        broker.connect("escuchadno");
        broker.subscribe("dds2024/heladera/medrano/alerta");

        receptorMovimiento.evaluarDatosSensor("activado",heladera);
        broker.disconnect();
        assert heladera.getIncidentes().size() == 1;
    }
}
