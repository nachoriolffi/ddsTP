package ar.edu.utn.frba.dds.models.entities.cargaMasiva;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public static Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
