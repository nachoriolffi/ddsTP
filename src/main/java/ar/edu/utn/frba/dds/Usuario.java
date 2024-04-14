package ar.edu.utn.frba.dds;

public class Usuario {
    private String nombreUsuario;
    private String contrasenia;
    //private Persona persona;
    //TODO
    public Usuario(String nombre, String contrasenia) {
        this.nombreUsuario = nombre;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}
