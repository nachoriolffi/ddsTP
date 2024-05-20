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

import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestCorreo {

    /*@Test
    public void testCorreo() {
        Colaborador colaborador = new Colaborador();
        Contacto contacto = new Contacto("Mail", "federperez@frba.utn.edu.ar");
        MedioDeComunicacion correo = new CorreoElectronico();
        Mensaje mensaje = new Mensaje("prueba", "hola");



    }*/


}
