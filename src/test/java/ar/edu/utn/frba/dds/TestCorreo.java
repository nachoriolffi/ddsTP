package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.AdapterCorreo;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.CorreoElectronico;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.ServicioMail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    private Notificacion notificacion;

    @BeforeEach
    public void setUp() {
        // Crear mocks
        mockAdapterCorreo = Mockito.mock(AdapterCorreo.class);

        // Crear la instancia de CorreoElectronico usando el mock del adaptador
        correoElectronico = new CorreoElectronico(mockAdapterCorreo);

        // Crear instancias de Mensaje y Colaborador
        mensaje = new Mensaje("Titulo de Mensaje para Gaston", "Holaaa Gaston, ¿cómo va?");
        destinatario = new Colaborador("Nombre del destinatario", "Apellido del destinatario",
                new ArrayList<>(), new ArrayList<>(), null, null);

        destinatario.getContacto().add(new Contacto(TipoContacto.MAIL,"federperez@frba.utn.edu.ar"));
        notificacion= new Notificacion( destinatario.getContacto(), mensaje);
    }

    @Test
    public void testComunicar() {
        // Llamar al método comunicar
        correoElectronico.comunicar(notificacion);

        // Verificar que el método comunicarMensaje del adaptador fue llamado una vez con los parámetros correctos
        verify(mockAdapterCorreo, times(1)).comunicarMensaje(mensaje, destinatario.getContacto().get(0));
    }

    @Test
    public void testEnviarCorreo() throws Exception {
        // Crear un mock de la clase ServicioMail
        ServicioMail mockServicioMail = Mockito.mock(ServicioMail.class);

        // Crear una instancia de CorreoElectronico usando el mock del servicio de mail
        correoElectronico = new CorreoElectronico(new AdapterCorreo());

        // Llamar al método comunicar
        correoElectronico.comunicar(notificacion);

        // Verificar que el método enviarCorreo del servicio de mail fue llamado una vez con los parámetros correctos
        verify(mockServicioMail, times(1)).enviarCorreo(destinatario.getContacto().get(0).getDescripcion(), mensaje.getTitulo(), mensaje.getMensaje());
    }

}
