package ar.edu.utn.frba.dds.lectorCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class  LectorDeCSV {

    public static void realizarCargaMasivaDatos(String path) throws FileNotFoundException {
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            List<String[]> records = csvReader.readAll();

            // Impresión de registros
            // habria que usar constructor colaborador
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

    }

    public LectorDeCSV() {
    }

    public void leerCSV(String ruta) {
        List<String[]> csvComoLista = csvALista(ruta);
        if (csvComoLista != null) {
            levantarObjetos(csvComoLista);
        } else {
            System.out.println("Error: No se pudo leer el archivo CSV.");
        }
    }

    public List<String[]> csvALista(String ruta) {
        try (Stream<String> streamFile = Files.lines(Paths.get(ruta))) {
            return streamFile.map(line -> line.split(",")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract void levantarObjetos(List<String[]> csvComoLista);
}
