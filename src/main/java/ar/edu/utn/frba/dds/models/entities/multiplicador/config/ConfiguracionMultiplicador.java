package ar.edu.utn.frba.dds.models.entities.multiplicador.config;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Observable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Getter
@Setter
public class ConfiguracionMultiplicador extends Observable {

    private static ConfiguracionMultiplicador instance;
    private Double multiplicadorViandasDistribuidas;
    private Double multiplicadorViandasDonadas;
    private Double multiplicadorRegistroVulnerables;
    private Double multiplicadorHeladeraActiva;
    private Double multiplicadorDinero;
    private static ConfiguracionMultiplicador instancia = null;

    private final String filePath = "configuracionMultiplicadores.json";

    private ConfiguracionMultiplicador() {
        // Constructor privado para el patrón Singleton
        cargarConfiguracion(filePath);
    }

    public static ConfiguracionMultiplicador getInstance() {
        if (instancia == null) {
            instancia = new ConfiguracionMultiplicador();
        }
        return instancia;
    }

    private void cargarConfiguracion(String filePath) {
        // Cargar archivo desde el classpath
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {

            if (inputStream == null) {
                throw new RuntimeException("El archivo de configuración no se encontró en el classpath: " + filePath);
            }

            // Parsear el archivo JSON
            JSONParser parser = new JSONParser();
            InputStreamReader reader = new InputStreamReader(inputStream);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject configuracion = (JSONObject) jsonObject.get("configuracion");

            // Asignar valores a las variables
            this.multiplicadorViandasDistribuidas = Double.parseDouble(configuracion.get("multiplicadorViandasDistribuidas").toString());
            this.multiplicadorViandasDonadas = Double.parseDouble(configuracion.get("multiplicadorViandasDonadas").toString());
            this.multiplicadorRegistroVulnerables = Double.parseDouble(configuracion.get("multiplicadorRegistroVulnerables").toString());
            this.multiplicadorHeladeraActiva = Double.parseDouble(configuracion.get("multiplicadorHeladeraActiva").toString());
            this.multiplicadorDinero = Double.parseDouble(configuracion.get("multiplicadorDinero").toString());

            System.out.println("Archivo de configuración cargado correctamente.");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar la configuración de multiplicadores", e);
        }
    }

    public void setMultiplicadorDinero(double nuevoValor) {
        this.multiplicadorDinero = nuevoValor;
        setChanged();
        notifyObservers();
    }

    public double getMultiplicadorDinero() {
        return this.multiplicadorDinero;
    }
}
