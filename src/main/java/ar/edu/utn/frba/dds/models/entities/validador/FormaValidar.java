package ar.edu.utn.frba.dds.models.entities.validador;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;

public interface FormaValidar {

    public boolean validar(String contrasenia, Usuario usuario);
}
