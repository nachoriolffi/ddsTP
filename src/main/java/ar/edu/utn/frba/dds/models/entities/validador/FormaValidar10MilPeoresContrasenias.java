package ar.edu.utn.frba.dds.models.entities.validador;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FormaValidar10MilPeoresContrasenias implements FormaValidar {

    private static List<String> contrasenias = new ArrayList<>();
    private static final String RESOURCE_PATH = "10000Peores.txt"; // Nombre del archivo en src/main/resources

    public FormaValidar10MilPeoresContrasenias() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(RESOURCE_PATH)), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contrasenias.add(line);
            }
            contrasenias.sort(String::compareTo); // Ordenar la lista para optimizar las búsquedas
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Error cargando contraseñas comunes: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean validar(String contrasenia, Usuario usuario) {
        // Realizar búsqueda binaria para mayor eficiencia en listas ordenadas
        return !contrasenias.contains(contrasenia); // Cambiar a binarySearch si deseas más eficiencia
    }
}







