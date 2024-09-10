package ar.edu.utn.frba.dds.models.entities.validador;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;

import java.io.IOException;

public class FormaValidarLongitud implements FormaValidar {
    private static final int MINIMO = 8;
    private static final int MAXIMO = 64;

    public boolean validar(String contrasenia, Usuario usuario) {
       if ( !(contrasenia.length() >= FormaValidarLongitud.MINIMO && contrasenia.length() <= FormaValidarLongitud.MAXIMO))
       {
           IOException e = new IOException("La contraseña debe tener entre 8 y 64 caracteres");
              System.err.println("Error leyendo archivo: " + e.getMessage());
       }
        return true;
    }

    //constructor
    public FormaValidarLongitud() {
    }
}
