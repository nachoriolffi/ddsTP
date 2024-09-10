package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.entities.contacto.correo.CorreoElectronico;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.Telegram;
import ar.edu.utn.frba.dds.models.entities.contacto.wpp.NotifcarPorWpp;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class MedioComunicacionAtributeConvertere implements AttributeConverter<MedioDeComunicacion, String> {

    @Override
    public String convertToDatabaseColumn(MedioDeComunicacion attribute) {
        if(attribute == null) return null;

        String medioDeComunicacion="";

        if(attribute instanceof Telegram){
            medioDeComunicacion = "Telegram";
        } else if (attribute instanceof NotifcarPorWpp) {
            medioDeComunicacion = "WPP";
        } else if (attribute instanceof CorreoElectronico) {
            medioDeComunicacion = "CorreoElectronico";
        }

        return medioDeComunicacion;
    }

    @Override
    public MedioDeComunicacion convertToEntityAttribute(String dbData) {
        if ( dbData == null ) return null;

        MedioDeComunicacion medioDeComunicacion = null;

        switch (dbData) {
            case "Telegram":
                medioDeComunicacion = new Telegram();
                break;
            case "WPP":
                medioDeComunicacion = new NotifcarPorWpp();
                break;
            case "CorreoElectronico":
                medioDeComunicacion = new CorreoElectronico();
                break;
        }

        return medioDeComunicacion;
    }
}
