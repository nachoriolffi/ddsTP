package ar.edu.utn.frba.dds.contacto.correo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ServicioMail {
    private static ServicioMail instancia = null;
    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("fedenotificacion@gmail.com", "bqcr olen uway ebro");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario, true));
            message.setSubject(asunto);
            message.setText(cuerpo);
            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException me) {
            System.out.println("Exception: " + me);
        }
    }

    public static ServicioMail getInstance() {
        if (instancia == null) {
            instancia = new ServicioMail();
        }
        return instancia;
    }
}