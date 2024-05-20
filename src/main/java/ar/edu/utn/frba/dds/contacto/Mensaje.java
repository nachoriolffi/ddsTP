package ar.edu.utn.frba.dds.contacto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensaje {
    private String titulo;
    private String mensaje;

    public Mensaje(String titulo, String mensaje) {
        this.titulo = titulo;
        this.mensaje = mensaje;
    }
    public Mensaje() {
    }
}
