package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.services.servicioDePuntos.AdapterPuntosDonacion;
import ar.edu.utn.frba.dds.services.servicioDePuntos.IAdapterPuntosDonacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestServicioDeTercero {

    private IAdapterPuntosDonacion adapter;

    @BeforeEach
    public void setUp() {
        adapter = new AdapterPuntosDonacion();
    }

    @Test
    public void testObtenerPuntosDonacion() throws IOException {
        double lat = -34.603722;
        double lon = -58.381592;
        List<String> puntos = adapter.obtenerPuntosDonacion(lat, lon);
        assertNotNull(puntos, "The list of points should not be null");
        assertTrue(puntos.size() > 0, "The list of points should not be empty");
    }

    @Test
    public void testCrearPuntoDonacion() throws IOException {
        String nombre = "Donación Centro 1";
        double lat = -34.603722;
        double lon = -58.381592;
        String direccion = "Calle Falsa 123";
        adapter.crearPuntoDonacion(nombre, lat, lon, direccion);
        // Assuming the service does not return a value, we just check for no exceptions
    }
}
