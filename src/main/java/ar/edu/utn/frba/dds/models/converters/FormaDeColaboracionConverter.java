package ar.edu.utn.frba.dds.models.converters;



import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FormaDeColaboracionConverter implements AttributeConverter<TipoColaboracion, String> {

    @Override
    public String convertToDatabaseColumn(TipoColaboracion tipoColaboracion) {
        if (tipoColaboracion == null) return null;
        return tipoColaboracion.name();
    }

    @Override
    public TipoColaboracion convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return TipoColaboracion.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown value: " + dbData);
        }
    }
}