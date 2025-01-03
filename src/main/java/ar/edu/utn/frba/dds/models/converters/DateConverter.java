package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.utils.Constant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<Date, String> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT);

    @Override
    public String convertToDatabaseColumn(Date date) {
        return (date == null ? null : formatter.format(date));
    }

    @Override
    public Date convertToEntityAttribute(String dateStr) {
        try {
            return (dateStr == null ? null : formatter.parse(dateStr));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error parsing date: " + dateStr, e);
        }
    }
}
