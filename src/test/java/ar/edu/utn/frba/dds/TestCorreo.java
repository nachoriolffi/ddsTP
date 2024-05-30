package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.contacto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestCorreo {

    /*@Test
    public void testCorreo() {
        Colaborador colaborador = new Colaborador();
        Contacto contacto = new Contacto("Mail", "federperez@frba.utn.edu.ar");
        MedioDeComunicacion correo = new CorreoElectronico();
        Mensaje mensaje = new Mensaje("prueba", "hola");
    }*/
    private CorreoElectronico correoElectronico;
    private AdapterCorreo mockAdapterCorreo;
    private Mensaje mensaje;
    private Colaborador destinatario;

    @BeforeEach
    public void setUp() {
        // Crear mocks
        mockAdapterCorreo = Mockito.mock(AdapterCorreo.class);

        // Crear la instancia de CorreoElectronico usando el mock del adaptador
        correoElectronico = new CorreoElectronico(mockAdapterCorreo);

        // Crear instancias de Mensaje y Colaborador
        mensaje = new Mensaje("Titulo de Mensaje para Gaston", "Holaaa Gaston, ¿cómo va?");
        destinatario = new Colaborador("Nombre del destinatario", "Apellido del destinatario",
                new ArrayList<>(), new ArrayList<>(), null, null,
                new Contacto("mail","federperez@frba.utn.edu.ar"));
    }

    @Test
    public void testComunicar() {
        // Llamar al método comunicar
        correoElectronico.comunicar(mensaje, destinatario);

        // Verificar que el método comunicarMensaje del adaptador fue llamado una vez con los parámetros correctos
        verify(mockAdapterCorreo, times(1)).comunicarMensaje(mensaje, destinatario);
    }

    @Test
    public void testEnviarCorreo() throws Exception {
        // Crear un mock de la clase ServicioMail
        ServicioMail mockServicioMail = Mockito.mock(ServicioMail.class);

        // Crear una instancia de CorreoElectronico usando el mock del servicio de mail
        correoElectronico = new CorreoElectronico(new AdapterCorreo());

        // Llamar al método comunicar
        correoElectronico.comunicar(mensaje, destinatario);

        // Verificar que el método enviarCorreo del servicio de mail fue llamado una vez con los parámetros correctos
        verify(mockServicioMail, times(1)).enviarCorreo(destinatario.getContacto().getDescripcion(), mensaje.getTitulo(), mensaje.getMensaje());
    }

}
