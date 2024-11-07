package ar.edu.utn.frba.dds.services.servicioDePuntos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class servicioPrueba {
    private static final String BASE_URL = "http://127.0.0.1:8000";

    public static void main(String[] args) {

        // Realizar una solicitud GET
        //getPuntosDonacion();

        // Realizar una solicitud POST
        crearPuntoDonacion();
    }
    // Método para realizar una solicitud GET
    public static void getPuntosDonacion() {
        try {
            URL url = new URL(BASE_URL + "/recomendaciones/?lat=-34.603722&long=-58.381592");
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

                // Imprimir la respuesta
                System.out.println("GET Response: " + response.toString());
            } else {
                System.out.println("GET request failed. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para realizar una solicitud POST
    public static void crearPuntoDonacion() {
        try {
            URL url = new URL(BASE_URL + "/puntos/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true); // Permitir enviar datos

            // Crear el JSON del cuerpo de la solicitud
            String jsonInputString = "[{\"nombre\":\"Donación Centro 1\",\"lat\":-34.603722,\"long\":-58.381592,\"direccion\":\"Calle Falsa 123\"}]";

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

