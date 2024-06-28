package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.AdapterTelegram;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.IAdapterTelegram;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.Telegram;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestTelegram {

    
    private IAdapterTelegram mockAdapterTelegram;


    private Telegram telegram;

    private Mensaje mensaje;
    private Colaborador destinatario;

    private Notificacion notificacion;

    @BeforeEach
    public void setUp(){


        mensaje = new Mensaje("Hola", "¿cómo va?");

        destinatario = new Colaborador("Nombre del destinatario", "Apellido del destinatario",
                new ArrayList<>(), new ArrayList<>(), null, null);

        destinatario.getContacto().add(new Contacto(TipoContacto.TELEGRAM, "831963423"));
        notificacion= new Notificacion( destinatario.getContacto(), mensaje);

    }

    @Test
    public void testComunicarTelegram() {

        mensaje = new Mensaje("Hola", "¿cómo va?");

        destinatario = new Colaborador("Nombre del destinatario", "Apellido del destinatario",
                new ArrayList<>(), new ArrayList<>(), null, null);

        destinatario.getContacto().add(new Contacto(TipoContacto.TELEGRAM, "831963423"));
        notificacion= new Notificacion( destinatario.getContacto(), mensaje);

        mockAdapterTelegram = Mockito.mock(IAdapterTelegram.class);

        telegram = new Telegram(mockAdapterTelegram);   //Por algun motivo el test no corre si lo meto en setUp

        //destinatario.setContacto(new Contacto("telegramPrueba", "1125253216"));

        telegram.comunicar(notificacion);

        // Verificar que el método comunicarMensaje del adaptador fue llamado una vez con los parámetros correctos
        verify(mockAdapterTelegram, times(1)).comunicarMensaje(mensaje, destinatario.getContacto().get(0));
    }

    @Test
    public void testEnviarTelegram() {
        mensaje = new Mensaje("Hola", "¿cómo va?");

        destinatario = new Colaborador("Nombre del destinatario", "Apellido del destinatario",
                new ArrayList<>(), new ArrayList<>(), null, null);

        destinatario.getContacto().add(new Contacto(TipoContacto.TELEGRAM, "831963423"));
        notificacion= new Notificacion( destinatario.getContacto(), mensaje);


        //destinatario = new Colaborador("Nombre del destinatario", "Apellido del destinatario", new ArrayList<>(), new ArrayList<>(), null, null);

        mockAdapterTelegram = Mockito.mock(AdapterTelegram.class);
        telegram = new Telegram(new AdapterTelegram());

        telegram.comunicar(notificacion);

    }

}
