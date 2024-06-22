package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.AdapterTelegram;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.Telegram;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestTelegram {

    
    private AdapterTelegram mockAdapterTelegram;


    private Telegram telegram;

    private Mensaje mensaje;
    private Colaborador destinatario;

    @BeforeEach
    public void setUp() {


        mensaje = new Mensaje("Hola", "¿cómo va?");

        destinatario = new Colaborador("Nombre del destinatario", "Apellido del destinatario",
                new ArrayList<>(), new ArrayList<>(), null, null,
                new Contacto("telegramPrueba", "1125253216"));
    }

    @Test
    public void testComunicarTelegram() {

        mockAdapterTelegram = Mockito.mock(AdapterTelegram.class);

        telegram = new Telegram(mockAdapterTelegram);   //Por algun motivo el test no corre si lo meto en setUp

        //destinatario.setContacto(new Contacto("telegramPrueba", "1125253216"));

        telegram.comunicar(mensaje, destinatario);

        // Verificar que el método comunicarMensaje del adaptador fue llamado una vez con los parámetros correctos
        verify(mockAdapterTelegram, times(1)).comunicarMensaje(mensaje, destinatario);
    }

    @Test
    public void testEnviarTelegram() {
        mensaje = new Mensaje("Hola", "¿cómo va?");

        destinatario = new Colaborador("Nombre del destinatario", "Apellido del destinatario",
                new ArrayList<>(), new ArrayList<>(), null, null,
                new Contacto("Mati Tisco", "831963423"));

        mockAdapterTelegram = Mockito.mock(AdapterTelegram.class);
        telegram = new Telegram(new AdapterTelegram());

        telegram.comunicar(mensaje, destinatario);

    }

}
