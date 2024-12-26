package ar.edu.utn.frba.dds.models.entities.multiplicador.config;

import ar.edu.utn.frba.dds.utils.Constant;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Getter
@Setter
public class MultiplicadorPuntos {

    private static MultiplicadorPuntos instance;
    private static Double multiplicadorViandasDistribuidas;
    private static Double multiplicadorViandasDonadas;
    private static Double multiplicadorRegistroVulnerables;
    private static Double multiplicadorHeladeraActiva;
    private static Double multiplicadorDinero;

    private MultiplicadorPuntos() {
        cargarConfiguracion();
    }

    public static MultiplicadorPuntos getInstance() {
        if (instance == null) {
            instance = new MultiplicadorPuntos();
        }
        return instance;
    }

    private void cargarConfiguracion() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constant.PATH_MULTIPLICADORES)) {
            assert inputStream != null;
            try (InputStreamReader reader = new InputStreamReader(inputStream)) {

                JSONObject configuracion = (JSONObject) new JSONParser().parse(reader);
                JSONObject valores = (JSONObject) configuracion.get("configuracion");

                multiplicadorViandasDistribuidas = Double.parseDouble(valores.get("multiplicadorViandasDistribuidas").toString());
                multiplicadorViandasDonadas = Double.parseDouble(valores.get("multiplicadorViandasDonadas").toString());
                multiplicadorRegistroVulnerables = Double.parseDouble(valores.get("multiplicadorRegistroVulnerables").toString());
                multiplicadorHeladeraActiva = Double.parseDouble(valores.get("multiplicadorHeladeraActiva").toString());
                multiplicadorDinero = Double.parseDouble(valores.get("multiplicadorDinero").toString());

                System.out.println("Configuración cargada correctamente.");

            }
        } catch (IOException | ParseException | NullPointerException e) {
            throw new RuntimeException("Error al cargar la configuración", e);
        }
    }
}
