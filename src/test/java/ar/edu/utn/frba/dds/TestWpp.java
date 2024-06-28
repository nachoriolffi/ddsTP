package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.wpp.AdapterWhatsapp;
import ar.edu.utn.frba.dds.models.entities.contacto.wpp.NotifcarPorWpp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TestWpp {

    private AdapterWhatsapp mokceoAdapterWpp;

    private NotifcarPorWpp notifcarPorWpp;

    private Colaborador colaborador;

    private Mensaje mensaje;

    private List<Contacto> contacto;

    private Notificacion notificacion;
    @BeforeEach
    public void setUp() {

        mokceoAdapterWpp = Mockito.mock(AdapterWhatsapp.class);

        notifcarPorWpp = new NotifcarPorWpp(mokceoAdapterWpp);

        colaborador= new Colaborador();

        mensaje = new Mensaje("Hola", "Hola");

        contacto = new ArrayList<Contacto>();

        contacto.add(new Contacto(TipoContacto.WPP, "+5491125253216"));

        colaborador.setContacto(contacto);

        notificacion = new Notificacion(contacto,mensaje);

    }

    @Test
    public void testComunicar() {

        //colaborador.setContacto(new Contacto("wpp", "1125253216"));

        notifcarPorWpp.comunicar(notificacion);

        Mockito.verify(mokceoAdapterWpp, Mockito.times(1)).comunicarMensaje(mensaje.getMensaje(), colaborador.getContacto().get(0).getDescripcion());

    }

    // NO LO DESCOMENTEN, NO HAY PLATA PARA PAGAR EL WPP
    // GASTA PLATA DE LA PRUEBA PARA PROBARLO
    // ALERTA DE NO TOCAR, SINO LES PEGO HACHAZO EN LA HELADERA
    @Test
    public void testComunicarRealNoUsarPorqNosCobranYNoHayPlata() {

       NotifcarPorWpp notificador = new NotifcarPorWpp(new AdapterWhatsapp());

        notificador.comunicar(notificacion);

    }

}
