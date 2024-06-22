package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
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

    @BeforeEach
    public void setUp(){
        receptorMovimiento= new ReceptorMovimiento();
        receptorTemperatura= new ReceptorTemperatura();

        modeloHeladera = new ModeloHeladera(12.5,0.0,90.0,15);
    }

    @Test
    public void TestTemperatura(){

    }
}
