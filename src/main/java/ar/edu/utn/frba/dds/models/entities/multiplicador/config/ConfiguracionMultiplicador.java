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

    private String path = "../src/main/resources/configuracionMultiplicadores.json";

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
        // Obtener la ruta absoluta del archivo
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.out.println("Ruta absoluta del archivo de configuración: " + absolutePath);

        // Verificar si el archivo existe
        if (!file.exists()) {
            throw new RuntimeException("El archivo de configuración no existe: " + absolutePath);
        }

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            System.out.println("Archivo de configuración encontrado. Leyendo contenido desde: " + absolutePath);

            // Parsear el contenido del archivo
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject configuracion = (JSONObject) jsonObject.get("configuracion");

            // Asignar valores a las variables
            this.multiplicadorViandasDistribuidas = Double.parseDouble(configuracion.get("multiplicadorViandasDistribuidas").toString());
            this.multiplicadorViandasDonadas = Double.parseDouble(configuracion.get("multiplicadorViandasDonadas").toString());
            this.multiplicadorRegistroVulnerables = Double.parseDouble(configuracion.get("multiplicadorRegistroVulnerables").toString());
            this.multiplicadorHeladeraActiva = Double.parseDouble(configuracion.get("multiplicadorHeladeraActiva").toString());
            this.multiplicadorDinero = Double.parseDouble(configuracion.get("multiplicadorDinero").toString());

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
