package ar.edu.utn.frba.dds.models.entities.validador;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FormaValidar10MilPeoresContrasenias implements FormaValidar {


    public FormaValidar10MilPeoresContrasenias() {
    }
    
    private static final String path = "/src/main/java/ar/edu/utn/frba/dds/10000Peores.txt";

    public boolean validar(String contrasenia, Usuario usuario) {
        String workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory = " + workingDirectory);
        System.out.println("WD + path = " + workingDirectory + path);
        try (BufferedReader br = new BufferedReader(new FileReader(workingDirectory + path))) {
            String line;
            while ((line = br.readLine()) != null) {
                int cmp = line.compareTo(contrasenia);
                if (cmp == 0) {
                    // encontro la palabra
                    return false;
                } else if (cmp > 0) { //el true y false estan invertidos para conservar que si devuelve true es que paso la validacion
                    // ya paso por el lugar en el que tendria que estar, por lo que se deja de buscar
                    return true;
                }
            }
            // termino el archivo y no la encontro todavia
            return false;
        } catch (IOException e) {
            System.err.println("Error leyendo archivo: " + e.getMessage());
            return false;
        }
    }


}
