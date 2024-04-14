package ar.edu.utn.frba.dds.validador;

import ar.edu.utn.frba.dds.Usuario;

public interface FormaValidar {

    public boolean validar(String contrasenia, Usuario usuario);
}
