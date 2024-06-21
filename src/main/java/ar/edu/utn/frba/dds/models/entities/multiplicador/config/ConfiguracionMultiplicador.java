package ar.edu.utn.frba.dds.models.entities.multiplicador.config;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Observable;
import java.io.FileReader;

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
    private String path = "src/main/java/ar/edu/utn/frba/dds/reconocimiento/config/configuracionMultiplicadores.json";

    public ConfiguracionMultiplicador() {
        // Constructor privado para el patrón Singleton
        cargarConfiguracion(path);
    }

    public static ConfiguracionMultiplicador getInstance() {
        if (instancia == null) {
            instancia = new ConfiguracionMultiplicador();
        }
        return instancia;
    }

    private void cargarConfiguracion(String path) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject configuracion = (JSONObject) jsonObject.get("configuracion");
            this.multiplicadorViandasDistribuidas = Double.parseDouble(configuracion.get("multiplicadorViandasDistribuidas").toString());
            this.multiplicadorViandasDonadas = Double.parseDouble(configuracion.get("multiplicadorViandasDonadas").toString());
            this.multiplicadorRegistroVulnerables = Double.parseDouble(configuracion.get("multiplicadorRegistroVulnerables").toString());
            this.multiplicadorHeladeraActiva = Double.parseDouble(configuracion.get("multiplicadorHeladeraActiva").toString());
            this.multiplicadorDinero = Double.parseDouble(configuracion.get("multiplicadorDinero").toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // Valores por defecto si hay un error al cargar el archivo
            throw new RuntimeException("Error al cargar la configuración de multiplicadores");
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
