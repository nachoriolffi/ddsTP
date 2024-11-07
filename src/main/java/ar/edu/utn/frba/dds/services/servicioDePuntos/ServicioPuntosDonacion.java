
package ar.edu.utn.frba.dds.services.servicioDePuntos;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ServicioPuntosDonacion {

    private static final String BASE_URL = "http://127.0.0.1:8000";

    public List<String> getPuntosDonacion(double lat, double lon) throws IOException {
        List<String> puntos = new ArrayList<>();
        try {
            URL url = new URL(BASE_URL + "/recomendaciones/?lat=" + lat + "&long=" + lon);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("GET Response: " + response.toString());
                // Procesar la respuesta y agregar los puntos a la lista
                // puntos = parsearRespuesta(response.toString());
            } else {
                System.out.println("GET request failed. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return puntos;
    }

    public void crearPuntoDonacion(String nombre, double lat, double lon, String direccion) throws IOException {
        try {
            URL url = new URL(BASE_URL + "/puntos/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true); // Permitir enviar datos

            //uso esto para no correr con riego de mala instanciacion de datos de esta manera el json se crea correctamente
            JSONObject punto = new JSONObject();
            punto.put("nombre", nombre);
            punto.put("lat", lat);
            punto.put("long", lon);
            punto.put("direccion", direccion);

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(punto);

            String jsonInputString = jsonArray.toString();

            // Enviar datos
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Leer la respuesta
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Punto de donación creado con éxito.");
            } else {
                System.out.println("Fallo en la creación. Código de respuesta: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}