package ar.edu.utn.frba.dds.models.entities.contacto.factory;

import ar.edu.utn.frba.dds.models.entities.contacto.correo.CorreoElectronico;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioComunicacion;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.Telegram;
import ar.edu.utn.frba.dds.models.entities.contacto.wpp.NotifcarPorWpp;

public class MedioComunicacionFactory {

    public MedioComunicacionFactory() {
    }

    public static MedioComunicacion createMedioDeComunicacion(String tipo) {
        switch (tipo) {
            case "TELEGRAM":
                return new Telegram();
            case "WPP":
                return new NotifcarPorWpp();
            case "MAIL":
                return new CorreoElectronico();
            default:
                throw new IllegalArgumentException("Tipo de medio de comunicación no soportado: " + tipo);
        }
    }

}
