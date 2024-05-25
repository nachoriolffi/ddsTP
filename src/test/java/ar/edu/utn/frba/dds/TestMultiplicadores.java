package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.formas.FormaDeColaboracion;
import ar.edu.utn.frba.dds.colaborador.formas.DonacionDinero;
import ar.edu.utn.frba.dds.colaborador.formas.TipoColaboracion;
import ar.edu.utn.frba.dds.config.ConfiguracionMultiplicador;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestMultiplicadores {

    @Test
    public void testRecibirMultiplicadorDinero() throws Exception {
        DonacionDinero donacionDinero= new DonacionDinero(1000, TipoColaboracion.DINERO,new Date());

        System.out.println("Multiplicador antes de modificar: "+donacionDinero.getMultiplicador());

        ConfiguracionMultiplicador configuracionMultiplicador = ConfiguracionMultiplicador.getInstance();
        configuracionMultiplicador.setMultiplicadorDinero(5.0);

        // Notificar manualmente la actualización a la instancia de DonacionDinero
        donacionDinero.update(configuracionMultiplicador, null);

        System.out.println("Multiplicador despues de modificar: "+donacionDinero.getMultiplicador());

        assert donacionDinero.getMultiplicador() == 5.0;
      }


}
