package ar.edu.utn.frba.dds.lectorCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;



public class LectorDeCSV {

    public static void realizarCargaMasivaDatos (String path) throws FileNotFoundException {
        String archivoCarga = path;
        try (CSVReader csvReader = new CSVReader(new FileReader(archivoCarga))) {
            List<String[]> records = csvReader.readAll();

            // Impresión de registros
            // habria que usar constructor colaborador
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

    }

    public LectorDeCSV() {
    }
}
