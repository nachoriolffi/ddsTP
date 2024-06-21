package ar.edu.utn.frba.dds.contacto.wpp;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class AdapterWhatsapp implements IAdapterWpp{

    public static final String ACCOUNT_SID = "AC9b12a3332b7948ecd60d89b89b90442f";
    //System.getenv("TWILIO_ACCOUNT_SID")
    //AC14da632dd9bddbd9e38836220a108a25
    public static final String AUTH_TOKEN = "e037581ee0eae1a284f6db2bb3e36f4d";
    //System.getenv("TWILIO_ACCOUNT_TOKEN")
    //b12d68929ed6c91df5d3f78ba3b2a973
    @Override
    public void comunicarMensaje(String mensaje, String destinatario) {
        enviarWhatsapp(destinatario, mensaje);
    }

    private void enviarWhatsapp(String destinatario, String mensaje) {
        final String telefonoDestino = String.format("whatsapp:%s", destinatario);
        final String telefonoOrigen = "whatsapp:+14155238886";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(telefonoDestino),
                new com.twilio.type.PhoneNumber(telefonoOrigen),
                mensaje
        ).create();
    }
}
