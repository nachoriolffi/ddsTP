package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.entities.contacto.correo.CorreoElectronico;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioComunicacion;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.Telegram;
import ar.edu.utn.frba.dds.models.entities.contacto.wpp.NotifcarPorWpp;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class MedioComunicacionConverter implements AttributeConverter<MedioComunicacion, String> {

    @Override
    public String convertToDatabaseColumn(MedioComunicacion attribute) {
        if (attribute == null) return null;

        String medioDeComunicacion = new String();

        if (attribute instanceof Telegram) {
            medioDeComunicacion = "TELEGRAM";
        } else if (attribute instanceof NotifcarPorWpp) {
            medioDeComunicacion = "WPP";
        } else if (attribute instanceof CorreoElectronico) {
            medioDeComunicacion = "EMAIL";
        }

        return medioDeComunicacion;
    }

    @Override
    public MedioComunicacion convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        MedioComunicacion medioComunicacion = null;

        switch (dbData) {
            case "TELEGRAM":
                medioComunicacion = new Telegram();
                break;
            case "WPP":
                medioComunicacion = new NotifcarPorWpp();
                break;
            case "EMAIL":
                medioComunicacion = new CorreoElectronico();
                break;
        }

        return medioComunicacion;
    }
}
