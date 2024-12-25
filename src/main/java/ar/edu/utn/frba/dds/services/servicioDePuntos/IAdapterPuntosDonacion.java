package ar.edu.utn.frba.dds.services.servicioDePuntos;

import java.io.IOException;
import java.util.List;

public interface IAdapterPuntosDonacion {
    //List<String> obtenerPuntosDonacion() throws IOException;
    //void crearPuntoDonacion(String nombre, double lat, double lon, String direccion) throws IOException;

    List<String> obtenerPuntosDonacion(double lat, double lon) throws IOException;
    void crearPuntoDonacion(String nombre, double lat, double lon, String direccion) throws IOException;
}
