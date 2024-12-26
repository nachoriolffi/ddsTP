package ar.edu.utn.frba.dds.services.servicioDePuntos;

import java.io.IOException;
import java.util.List;

/*public class AdapterPuntosDonacion implements IAdapterPuntosDonacion {

    private ServicioPuntosDonacion servicioPuntosDonacion;

    public AdapterPuntosDonacion() {
        this.servicioPuntosDonacion = new ServicioPuntosDonacion();
    }

    @Override
    public List<String> obtenerPuntosDonacion() throws IOException {
        // Llama al método getPuntosDonacion y procesa la respuesta
        // Aquí se asume que el método getPuntosDonacion devuelve una lista de puntos en formato JSON
        List<String> puntos = new ArrayList<>();


        // Lógica para obtener y procesar los puntos de donación
        // Por ejemplo, puedes parsear la respuesta JSON y agregar los puntos a la lista
        return puntos;
    }

    @Override
    public void crearPuntoDonacion(String nombre, double lat, double lon, String direccion) throws IOException {
        // Llama al método crearPuntoDonacion con los parámetros adecuados
        // Aquí se asume que el método crearPuntoDonacion acepta un JSON con los datos del punto de donación
        String jsonInputString = String.format("[{\"nombre\":\"%s\",\"lat\":%f,\"long\":%f,\"direccion\":\"%s\"}]", nombre, lat, lon, direccion);
       // servicioPuntosDonacion.crearPuntoDonacion(jsonInputString);
    }
}*/


public class AdapterPuntosDonacion implements IAdapterPuntosDonacion {

    private final ServicioPuntosDonacion servicioPuntosDonacion;

    public AdapterPuntosDonacion() {
        this.servicioPuntosDonacion = new ServicioPuntosDonacion();
    }

    @Override
    public List<String> obtenerPuntosDonacion(double lat, double lon) throws IOException {
        return servicioPuntosDonacion.getPuntosDonacion(lat, lon);
    }

    @Override
    public void crearPuntoDonacion(String nombre, double lat, double lon, String direccion) throws IOException {
        servicioPuntosDonacion.crearPuntoDonacion(nombre, lat, lon, direccion);
    }
}
