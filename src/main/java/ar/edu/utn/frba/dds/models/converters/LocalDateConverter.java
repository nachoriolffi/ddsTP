package ar.edu.utn.frba.dds.models.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true) // Indica que este conversor se aplicará automáticamente
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) {
            return null; // Maneja el caso nulo
        }
        return Date.valueOf(localDate); // Convierte LocalDate a java.sql.Date
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        if (date == null) {
            return null; // Maneja el caso nulo
        }
        return date.toLocalDate(); // Convierte java.sql.Date a LocalDate
    }
}
