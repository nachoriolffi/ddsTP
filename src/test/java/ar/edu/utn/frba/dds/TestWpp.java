package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.contacto.Contacto;
import ar.edu.utn.frba.dds.contacto.Mensaje;
import ar.edu.utn.frba.dds.contacto.wpp.AdapterWhatsapp;
import ar.edu.utn.frba.dds.contacto.wpp.NotifcarPorWpp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestWpp {

    private AdapterWhatsapp mokceoAdapterWpp;

    private NotifcarPorWpp notifcarPorWpp;

    private Colaborador colaborador;

    private Mensaje mensaje;

    @BeforeEach
    public void setUp() {

        mokceoAdapterWpp = Mockito.mock(AdapterWhatsapp.class);

        notifcarPorWpp = new NotifcarPorWpp(mokceoAdapterWpp);

        colaborador= new Colaborador();

        mensaje = new Mensaje("Hola", "Hola");

    }

    @Test
    public void testComunicar() {

        colaborador.setContacto(new Contacto("wpp", "1125253216"));

        notifcarPorWpp.comunicar(mensaje, colaborador);

        Mockito.verify(mokceoAdapterWpp, Mockito.times(1)).comunicarMensaje(mensaje.getMensaje(), colaborador.getContacto().getDescripcion());

    }

    // NO LO DESCOMENTEN, NO HAY PLATA PARA PAGAR EL WPP
    // GASTA PLATA DE LA PRUEBA PARA PROBARLO
    // ALERTA DE NO TOCAR, SINO LES PEGO HACHAZO EN LA HELADERA
    /*@Test
    public void testComunicarRealNoUsarPorqNosCobranYNoHayPlata() {

        colaborador.setContacto(new Contacto("wpp", "+5491125253216"));

       NotifcarPorWpp notificador = new NotifcarPorWpp(new AdapterWhatsapp());

        notificador.comunicar(mensaje, colaborador);

    }*/

}
